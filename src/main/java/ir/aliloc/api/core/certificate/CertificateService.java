/**
 * 12/31/2018
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.certificate;

import ir.aliloc.api.config.MessageConstant;
import ir.aliloc.api.core.purchase_course.IPurchaseCourseService;
import ir.aliloc.api.core.course.Course;
import ir.aliloc.api.core.course.CourseDTO;
import ir.aliloc.api.core.course.ICourseService;
import ir.aliloc.api.core.enums.ECoursePaymentType;
import ir.aliloc.api.core.enums.EQuizResult;
import ir.aliloc.api.core.models.init.MainModel;
import ir.aliloc.api.core.models.request.CourseIdRequest;
import ir.aliloc.api.core.payment.IPaymentService;
import ir.aliloc.api.core.payment.PaymentDTO;
import ir.aliloc.api.core.user.IUserService;
import ir.aliloc.api.core.user.models.User;
import ir.aliloc.api.core.user.models.UserDTO;
import ir.aliloc.api.core.user_quize.IUserQuizService;
import ir.aliloc.api.core.user_quize.UserQuiz;
import ir.aliloc.api.core.util.CustomMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.ws.rs.ForbiddenException;
import java.util.Calendar;
import java.util.List;

@Service
@Transactional
class CertificateService implements ICertificateService {
    @Autowired
    private ICertificateDAO mICertificateDAO;
    @Autowired
    private CustomMapperService mCustomMapperService;
    @Autowired
    private IUserService mIUserService;
    @Autowired
    private ICourseService mICourseService;
    @Autowired
    private IUserQuizService mIUserQuizService;
    @Autowired
    private IPaymentService mIPaymentService;
    @Autowired
    private IPurchaseCourseService mIPurchaseCourseService;

    @Override
    public void saveCertificate(Certificate certificate) throws Exception {
        mICertificateDAO.addCertificate(certificate);
    }

    @Override
    public List<CertificateDTO> getCertificateHistory() throws Exception {
        UserDTO userDTO = mIUserService.getUserDTO();
        List<Certificate> certificates = mICertificateDAO.getCertificateHistory(userDTO.getId());
        List<CertificateDTO> certificateDTOS = mCustomMapperService.certificateListToCertificateDTOList(certificates);
        for (CertificateDTO certificateDTO : certificateDTOS) {
            if (certificateDTO.getCourse().getPrice() != 0 && certificateDTO.getCourse().getPaymentType() == ECoursePaymentType.CERTIFICATE) {
                if (mIPurchaseCourseService.checkPurchaseCourse(userDTO.getId(), certificateDTO.getCourse().getId())) {
                    certificateDTO.setBought(true);
                } else {
                    certificateDTO.setBought(false);
                    certificateDTO.setUrl(null);
                }
            } else {
                certificateDTO.setBought(true);
            }
        }
        return certificateDTOS;
    }

    @Override
    public CertificateDTO getCertificate(Long userId, Long courseId) {
        Certificate certificate = mICertificateDAO.getCertificate(userId, courseId);
        if (certificate != null) {
            return mCustomMapperService.certificateToCertificateDTO(certificate);
        }
        return null;
    }

    @Override
    public MainModel<PaymentDTO> paymentCertificate(CourseIdRequest courseIdRequest) throws Exception {
        UserDTO userDTO = mIUserService.getUserDTO();
        //CertificateDTO certificateDTO = getCertificate(userDTO.getId(), courseIdRequest.getCourseId());
        MainModel<PaymentDTO> mainMode = new MainModel<>();
        PaymentDTO paymentDTO;
        //if (certificateDTO != null) {
        //   throw new ForbiddenException(MessageConstant.ERROR_INVALID_COURSE_PAYMENT);

        //}
        //todo refactor code we need one instance of course object refactor later please. thanks :)
        Course course = mICourseService.getMainCourseById(courseIdRequest.getCourseId());
        CourseDTO courseDTO = mICourseService.getCourseById(courseIdRequest.getCourseId());
        if (course.getQuiz() == null) {
            throw new ForbiddenException(MessageConstant.ERROR_USER_DONT_PASS_QUIZ);
        }
        UserQuiz quiz = mIUserQuizService.getCustomUserQuizByUserId(userDTO.getId(), course.getQuiz().getId());
        if (quiz.getEType() != EQuizResult.PASS) {
            throw new ForbiddenException(MessageConstant.ERROR_USER_DONT_PASS_QUIZ);
        }
        if (course.getPrice() != 0 && course.getECoursePaymentType() == ECoursePaymentType.CERTIFICATE) {
            paymentDTO = mIPaymentService.paymentZarinPallCourseStepOne(courseDTO);
            mainMode.setMessage(MessageConstant.MESSAGE_SUCCESS);
            mainMode.setStatusCode(HttpStatus.OK.value());
            mainMode.setResult(paymentDTO);
        }

        return mainMode;
    }

    @Override
    public Certificate createCertificate(Long userId, Long courseId) {
        Certificate certificate = new Certificate();
        User user = new User();
        user.setId(userId);
        Course course = new Course();
        course.setId(userId);
        certificate.setCourse(course);
        certificate.setUser(user);
        certificate.setTime(Calendar.getInstance().getTimeInMillis());
        return certificate;
    }
}
