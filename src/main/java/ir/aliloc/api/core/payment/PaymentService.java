/**
 * 12/15/2018
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.payment;

import ir.aliloc.api.config.GlobalConstant;
import ir.aliloc.api.config.MessageConstant;
import ir.aliloc.api.core.payment.zarinpal.PaymentGatewayImplementationService;
import ir.aliloc.api.core.payment.zarinpal.PaymentGatewayImplementationServicePortType;
import ir.aliloc.api.core.purchase_audio.IPurchaseAudioService;
import ir.aliloc.api.core.purchase_course.IPurchaseCourseService;
import ir.aliloc.api.core.book.BookDTO;
import ir.aliloc.api.core.book.IBookService;
import ir.aliloc.api.core.course.CourseDTO;
import ir.aliloc.api.core.course.ICourseService;
import ir.aliloc.api.core.enums.ECoursePaymentType;
import ir.aliloc.api.core.enums.EPaymentState;
import ir.aliloc.api.core.models.request.BookIdRequest;
import ir.aliloc.api.core.user.IUserService;
import ir.aliloc.api.core.user.models.UserPassDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.ws.rs.ForbiddenException;
import javax.xml.ws.Holder;
import java.util.Calendar;

@Service
@Transactional
class PaymentService implements IPaymentService {
    @Autowired
    private IPaymentDAO mIPaymentDAO;

    @Autowired
    private ModelMapper mModelMapper;

    @Autowired
    private IUserService mIUserService;

    @Autowired
    private PaymentGatewayImplementationService mPaymentGatewayImplementationService;

    @Autowired
    private IBookService mIBookService;

    @Autowired
    private IPurchaseAudioService mIPurchaseAudioService;

    @Autowired
    private IPurchaseCourseService mIPurchaseCourseService;

    @Autowired
    private ICourseService mICourseService;


    @Override
    public void savePayment(PaymentDTO paymentDTO) throws Exception {
        mIPaymentDAO.addPayment(mModelMapper.map(paymentDTO, Payment.class));
    }

    @Override
    public PaymentDTO paymentZarinBookPallStepOne(BookIdRequest bookIdRequest) throws Exception {
        Holder<String> authority = new Holder<>();
        Holder<Integer> status = new Holder<>();
        UserPassDTO mainObjectUser = mIUserService.getMainObjectUser();
        BookDTO book = mIBookService.getBookById(bookIdRequest.getBookId());
        if (book.getPrice() != 0 && mIPurchaseAudioService.checkBookBuyByUser(bookIdRequest.getBookId(), mainObjectUser.getId())) {
            PaymentGatewayImplementationServicePortType paymentGatewayImplementationServicePort = mPaymentGatewayImplementationService.getPaymentGatewayImplementationServicePort();
            paymentGatewayImplementationServicePort.paymentRequest(GlobalConstant.MERID, book.getPrice(), "مبین وان", "alikarimipour157@gmail.com", mainObjectUser.getMobile(), GlobalConstant.ZARINPAL_URL_CALL_BACK, status, authority);
        } else {
            throw new ForbiddenException(MessageConstant.ERROR_INVALID_BOOK_PAYMENT);
        }
        Payment payment = addPayment(bookIdRequest, null, authority, mainObjectUser, book.getPrice());
        return mModelMapper.map(payment, PaymentDTO.class);
    }

    @Override
    public boolean checkEcoursPaymentType(CourseDTO courseDTO) throws Exception {
        if (courseDTO.getPaymentType() == ECoursePaymentType.SECTION) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public PaymentDTO paymentZarinPallCourseStepOne(CourseDTO courseDTO) throws Exception {
        Holder<String> authority = new Holder<>();
        Holder<Integer> status = new Holder<>();
        UserPassDTO mainObjectUser = mIUserService.getMainObjectUser();
        if (courseDTO.getPrice() != 0 && !mIPurchaseCourseService.checkPurchaseCourse(mainObjectUser.getId(), courseDTO.getId())) {
            PaymentGatewayImplementationServicePortType paymentGatewayImplementationServicePort = mPaymentGatewayImplementationService.getPaymentGatewayImplementationServicePort();
            paymentGatewayImplementationServicePort.paymentRequest(GlobalConstant.MERID, courseDTO.getPrice(), "مبین وان", "", mainObjectUser.getMobile(), GlobalConstant.ZARINPAL_URL_CALL_BACK, status, authority);
        } else {
            throw new ForbiddenException(MessageConstant.ERROR_INVALID_COURSE_PAYMENT);
        }
        Payment payment = addPayment(null, courseDTO, authority, mainObjectUser, courseDTO.getPrice());
        return mModelMapper.map(payment, PaymentDTO.class);
    }

    private Payment addPayment(BookIdRequest bookIdRequest, CourseDTO courseDTO, Holder<String> authority, UserPassDTO mainObjectUser, int price) throws Exception {
        Payment payment = new Payment();
        payment.setAmount(price);
        payment.setPayKey(authority.value);
        payment.setPaymentState(EPaymentState.PEND);
        payment.setPayUrl(GlobalConstant.PAY_URL + authority.value);
        payment.setUserId(mainObjectUser.getId());
        if (bookIdRequest != null) {
            payment.setBookId(bookIdRequest.getBookId());
        } else if (courseDTO != null) {
            payment.setCourseId(courseDTO.getId());
        }
        payment.setTime(Calendar.getInstance().getTimeInMillis());
        mIPaymentDAO.addPayment(payment);
        return payment;
    }

    @Override
    public Payment loadPaymentByAuthority(String authority) throws Exception {
        return mIPaymentDAO.loadPayment(authority);
    }

    @Override
    public void updatePayment(Payment payment) throws Exception {
        mIPaymentDAO.updatePayment(payment);
    }

    @Override
    public void acceptPaymentStepTwo(Payment payment) throws Exception {
        Holder<Long> refId = new Holder<>();
        Holder<Integer> status = new Holder<>();
        PaymentGatewayImplementationServicePortType paymentGatewayImplementationServicePort = mPaymentGatewayImplementationService.getPaymentGatewayImplementationServicePort();
        paymentGatewayImplementationServicePort.paymentVerification(GlobalConstant.MERID, payment.getPayKey(), payment.getAmount(), status, refId);
        if (status.value == 100) {
            payment.setPaymentState(EPaymentState.SUCCESSFUL);
        } else if (status.value == 101) {
            payment.setPaymentState(EPaymentState.SUCCESSFUL_BEFORE);
        } else {
            payment.setPaymentState(EPaymentState.FAILED);
        }
    }
}
