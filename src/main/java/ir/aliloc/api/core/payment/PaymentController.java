/**
 * 26/1/2019
 * author: alikarimipour157@gmail.com
 */
package ir.aliloc.api.core.payment;

import ir.aliloc.api.config.MessageConstant;
import ir.aliloc.api.core.audio_book.AudioBook;
import ir.aliloc.api.core.certificate.ICertificateService;
import ir.aliloc.api.core.purchase_audio.IPurchaseAudioService;
import ir.aliloc.api.core.purchase_audio.PurchaseAudio;
import ir.aliloc.api.core.purchase_course.IPurchaseCourseService;
import ir.aliloc.api.core.purchase_course.PurchaseCourse;
import ir.aliloc.api.exception.CustomizeResponseEntityExceptionHandler;
import ir.aliloc.api.core.book.BookDTO;
import ir.aliloc.api.core.book.IBookService;
import ir.aliloc.api.core.course.Course;
import ir.aliloc.api.core.course.CourseDTO;
import ir.aliloc.api.core.course.ICourseService;
import ir.aliloc.api.core.enums.EPaymentState;
import ir.aliloc.api.core.models.init.MainModel;
import ir.aliloc.api.core.models.request.BookIdRequest;
import ir.aliloc.api.core.models.request.CourseIdRequest;
import ir.aliloc.api.core.user.models.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.ForbiddenException;
import java.util.Calendar;

@RestController
public class PaymentController {

    @Autowired
    private IPaymentService mIPaymentService;

    @Autowired
    private IPurchaseAudioService mIPurchaseAudioService;

    @Autowired
    private IPurchaseCourseService mIPurchaseCourseService;

    @Autowired
    private ModelMapper mModelMapper;

    @Autowired
    private ICourseService mICourseService;

    @Autowired
    private ICertificateService mICertificateService;
    @Autowired
    private IBookService mIBookService;


    @RequestMapping(value = "api/payment/zarinPall/book/stepOne", method = RequestMethod.POST)
    public ResponseEntity<MainModel<PaymentDTO>> paymentZarinPallBookStep1(@RequestBody BookIdRequest bookIdRequest) {
        MainModel<PaymentDTO> mainModel = new MainModel<>();

        try {
            mainModel.setResult(mIPaymentService.paymentZarinBookPallStepOne(bookIdRequest));
            mainModel.setMessage(MessageConstant.MESSAGE_SUCCESS);
            mainModel.setStatusCode(HttpStatus.OK.value());
            return new ResponseEntity<>(mainModel, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new CustomizeResponseEntityExceptionHandler().handleAllExceptions(e);
        }
    }

    @RequestMapping(value = "api/payment/zarinPall/course/stepOne", method = RequestMethod.POST)
    public ResponseEntity<MainModel<PaymentDTO>> paymentZarinPallCourseStep1(@RequestBody CourseIdRequest courseIdRequest) {
        MainModel<PaymentDTO> mainModel = new MainModel<>();

        try {


            CourseDTO courseDTO = mICourseService.getCourseById(courseIdRequest.getCourseId());
            if (mIPaymentService.checkEcoursPaymentType(courseDTO)) {
                PaymentDTO paymentDTO = mIPaymentService.paymentZarinPallCourseStepOne(courseDTO);
                System.out.println(paymentDTO.getPayKey());

                mainModel.setResult(paymentDTO);

                mainModel.setMessage(MessageConstant.MESSAGE_SUCCESS);
                mainModel.setStatusCode(HttpStatus.OK.value());
            } else {
                throw new ForbiddenException(MessageConstant.ERROR_JUST_CERTIFICATE_CAN_BUYED);
            }

            return new ResponseEntity<>(mainModel, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new CustomizeResponseEntityExceptionHandler().handleAllExceptions(e);
        }
    }

    @RequestMapping(value = "payment/zarinPall/stepTwo", method = RequestMethod.POST)
    public ResponseEntity<MainModel<PaymentDTO>> paymentZarinPallStep1(@RequestBody PaymentDTO paymentDTO) {
        MainModel<PaymentDTO> mainModel = new MainModel<>();
        try {
            Payment payment = mIPaymentService.loadPaymentByAuthority(paymentDTO.getPayKey());
            mIPaymentService.acceptPaymentStepTwo(payment);
            mIPaymentService.updatePayment(payment);
            if (payment.getPaymentState() == EPaymentState.SUCCESSFUL) {
                if (payment.getBookId() != 0) {
                    PurchaseAudio purchaseAudio = createPurchaseAudio(payment);
                    mIPurchaseAudioService.addAudioPurchase(purchaseAudio);
                } else if (payment.getCourseId() != 0) {
                    Course course = mICourseService.getMainCourseById(payment.getCourseId());
                    PurchaseCourse purchaseCourse = createPurchaseCourse(payment, course);
                    mIPurchaseCourseService.addCoursePurchase(purchaseCourse);
                    /*if (course.getECoursePaymentType() == ECoursePaymentType.SECTION) {
                    } else {
                        mIPurchaseCourseService.addCoursePurchase(purchaseCourse);
                        //Certificate certificate = mICertificateService.createCertificate(payment.getUserId(), course.getId());
                        //mICertificateService.saveCertificate(certificate);
                    }*/
                }
                mainModel.setStatusCode(HttpStatus.OK.value());
                mainModel.setMessage(MessageConstant.MESSAGE_SUCCESS);
            } else if (payment.getPaymentState() == EPaymentState.SUCCESSFUL_BEFORE) {
                mainModel.setStatusCode(HttpStatus.OK.value());
                mainModel.setMessage(MessageConstant.MESSAGE_SUCCESS);
            } else {
                mainModel.setStatusCode(HttpStatus.BAD_REQUEST.value());
                mainModel.setMessage(MessageConstant.PAYMENT_MESSAGE_UNSUCCESS);
            }
            mainModel.setResult(mModelMapper.map(payment, PaymentDTO.class));

            return new ResponseEntity<>(mainModel, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new CustomizeResponseEntityExceptionHandler().handleAllExceptions(e);
        }
    }

    private PurchaseAudio createPurchaseAudio(Payment payment) throws Exception {
        PurchaseAudio purchaseAudio = new PurchaseAudio();
        User user = new User();
        user.setId(payment.getUserId());
        AudioBook audioBook = new AudioBook();

        BookDTO book = mIBookService.getBookById(payment.getBookId());
        audioBook.setId(book.getAudio().getId());
        purchaseAudio.setAudioBook(audioBook);
        purchaseAudio.setUser(user);
        purchaseAudio.setPayment(payment);
        purchaseAudio.setTime(Calendar.getInstance().getTimeInMillis());
        return purchaseAudio;
    }

    private PurchaseCourse createPurchaseCourse(Payment payment, Course course) {
        PurchaseCourse purchaseCourse = new PurchaseCourse();
        User user = new User();
        user.setId(payment.getUserId());
        purchaseCourse.setCourse(course);
        purchaseCourse.setUser(user);
        purchaseCourse.setPayment(payment);
        purchaseCourse.setTime(Calendar.getInstance().getTimeInMillis());
        return purchaseCourse;
    }


    /*@RequestMapping(value = "api/lecture/subjects", method = RequestMethod.GET)
    public ResponseEntity<MainModel<List<LectureSubjectDTO>>> getAllLectureSubjectWhenUserLogin() {
        MainModel<List<LectureSubjectDTO>> mainModel = new MainModel<>();
        try {
            mainModel.setResult(mILectureSubjectService.getSubjectListWhithDownloaded());
            mainModel.setMessage(MessageConstant.MESSAGE_SUCCESS);
            mainModel.setStatusCode(HttpStatus.OK.value());
            return new ResponseEntity<>(mainModel, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new CustomizeResponseEntityExceptionHandler().handleAllExceptions(e);
        }
    }*/
}
