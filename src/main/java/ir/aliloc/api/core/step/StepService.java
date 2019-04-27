/**
 * 12/18/2018
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.step;

import ir.aliloc.api.config.MessageConstant;
import ir.aliloc.api.core.certificate.ICertificateService;
import ir.aliloc.api.core.course.CourseDTO;
import ir.aliloc.api.core.course.ICourseService;
import ir.aliloc.api.core.enums.ECoursePaymentType;
import ir.aliloc.api.core.enums.EQuizResult;
import ir.aliloc.api.core.multimedia.MultiMediaDTO;
import ir.aliloc.api.core.purchase_course.IPurchaseCourseService;
import ir.aliloc.api.core.quize.Quiz;
import ir.aliloc.api.core.user.IUserService;
import ir.aliloc.api.core.user_course.IUserCourseService;
import ir.aliloc.api.core.user_quize.IUserQuizService;
import ir.aliloc.api.core.user_quize.UserQuiz;
import ir.aliloc.api.core.user_step.IUserStepService;
import ir.aliloc.api.core.util.CustomMapperService;
import ir.aliloc.api.security.IAuthenticationFaced;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.ws.rs.ForbiddenException;
import java.util.*;

@Service
@Transactional
class StepService implements IStepService {

    @Autowired
    private IStepDAO mIStepDAO;
    @Autowired
    private ICourseService mICourseService;
    @Autowired
    private IUserCourseService mIUserCourseService;
    @Autowired
    private ICertificateService mICertificateService;
    @Autowired
    private IPurchaseCourseService mIPurchaseCourseService;
    @Autowired
    private IAuthenticationFaced mIAuthenticationFaced;
    @Autowired
    private CustomMapperService mCustomMapperService;
    @Autowired
    private IUserStepService mIUserStepService;
    @Autowired
    private IUserService mIUserService;
    @Autowired
    private IUserQuizService mIUserQuizService;

    @Override
    public List<StepDTO> getStepListOfCourse(long courseId) throws Exception {

        String username = mIAuthenticationFaced.getAuthentication().getName();
//        long userId = Long.parseLong(mIAuthenticationFaced.getAuthentication().getName());
        CourseDTO courseDTO = mICourseService.getCourseById(courseId);
        List<Step> stepList = mIStepDAO.getStepListByCourseId(courseId);
//        mIUserCourseService.addUserCourse(userId, courseId);
        HashMap<Long, StepDTO> stepDTOHashMap = new HashMap<>();
        for (Step step : stepList) {
            stepDTOHashMap.put(step.getId(), this.getStepById(step.getId()));
        }
        if (!username.equals("anonymousUser")) {
            long userId = Long.parseLong(username);
            List<Long> userStepIds = mIUserStepService.getUserStepIds(userId);
            //List<StepDTO> stepDTOList = mCustomMapperService.stepListToStepDTOList(stepList);
            for (Long id : userStepIds) {
                if (stepDTOHashMap.containsKey(id)) {
                    StepDTO stepDTO = stepDTOHashMap.get(id);
                    if (stepDTO.isPassed()){
                        stepDTO.setAllowed(true);
                    }
                }
            }
            checkPreRequired(userId, courseDTO, stepDTOHashMap);
        } else {
            checkPreRequired(0, courseDTO, stepDTOHashMap);
        }
        ArrayList<StepDTO> arrayList = new ArrayList<>(stepDTOHashMap.values());
        arrayList.get(0).setAllowed(true);
        for (int i = 1; i < arrayList.size(); i++) {
            if (!arrayList.get(i).isAllowed()) {
                StepDTO beforStepDTO = arrayList.get(i-1);
                if (beforStepDTO.isPassed()){
                    arrayList.get(i).setAllowed(true);
                    break;
                }
            }
        }
        return arrayList;
    }

    private void checkPreRequired(long userId, CourseDTO courseDTO, Map<Long, StepDTO> stepDTOMap) throws Exception {
        if (courseDTO.getPrice() != 0 && !mIPurchaseCourseService.checkPurchaseCourse(userId, courseDTO.getId()) && courseDTO.getPaymentType() == ECoursePaymentType.SECTION) {
            boolean isFirstAudio = true;
            for (StepDTO stepDTO : stepDTOMap.values()) {
                if (!isFirstAudio) {
                    for (MultiMediaDTO multimedia : stepDTO.getMultimedias()) {
                        multimedia.setUrl(null);
                    }
                } else {
                    isFirstAudio = false;
                }
            }
//            throw new ForbiddenException(MessageConstant.ERROR_PURCHASE_COURSE_NOT_BUY);
        }

        if (courseDTO.getPreCourse() != null && mICertificateService.getCertificate(userId, courseDTO.getPreCourse().getId()) == null) {
            throw new ForbiddenException(MessageConstant.ERROR_USER_COURSE_PRE_COURSE);
        }
        if (courseDTO.getExpireTime() < Calendar.getInstance().getTimeInMillis()) {
            throw new ForbiddenException(MessageConstant.ERROR_EXPIRE_COURSE);
        }
        //TODO check userCourse feasible day
        //todo min delay quiz
        /*UserCourse userCourse = mIUserCourseService.getMainUserCourse(userId,courseDTO.getId());
        if(userCourse!=null){
            long day = 24 * 60 * 60 * 1000;
            long timeInCourse = userCourse.getTime() + (courseDTO.getMaxFeasibleDays() * day);
            if (timeInCourse < Calendar.getInstance().getTimeInMillis()){

            }
        }else{

        }*/
    }


    @Override
    public StepDTO getStepById(long stepId) throws Exception {
        String username = mIAuthenticationFaced.getAuthentication().getName();
        Step step = mIStepDAO.getStepById(stepId);
        StepDTO stepDTO = mCustomMapperService.stepToStepDTO(step, false);
        Quiz quiz = step.getQuiz();
        if (!username.equals("anonymousUser")) {
            UserQuiz userQuiz = mIUserQuizService.getCustomUserQuizByUserId(Long.parseLong(username), quiz.getId());
            setUserQuiz(stepDTO, userQuiz);
        } else {
            UserQuiz userQuiz = mIUserQuizService.getCustomUserQuizByUserId(0, quiz.getId());
            setUserQuiz(stepDTO, userQuiz);
        }
        return stepDTO;
    }

    private void setUserQuiz(StepDTO stepDTO, UserQuiz userQuiz) {
        if (userQuiz != null) {
            stepDTO.setQuizPoint(userQuiz.getPoint());
            if (userQuiz.getEType() == EQuizResult.PASS) {
                stepDTO.setPassed(true);
            } else {
                stepDTO.setPassed(false);
            }
        } else {
            stepDTO.setPassed(false);
        }
    }
}
