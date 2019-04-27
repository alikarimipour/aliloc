/**
 * 12/17/2018
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.question;

import ir.aliloc.api.config.MessageConstant;
import ir.aliloc.api.core.certificate.ICertificateService;
import ir.aliloc.api.core.questionBank_question.IQuestionBankQuestionService;
import ir.aliloc.api.core.question_option.IQuestionOptionService;
import ir.aliloc.api.core.question_option.QuestionOption;
import ir.aliloc.api.core.user_course.IUserCourseService;
import ir.aliloc.api.core.user_quize.IUserQuizService;
import ir.aliloc.api.core.user_quize.UserQuiz;
import ir.aliloc.api.core.user_step.IUserStepService;
import ir.aliloc.api.security.IAuthenticationFaced;
import ir.aliloc.api.core.enums.EQuizResult;
import ir.aliloc.api.core.models.request.AnswerInitRequest;
import ir.aliloc.api.core.models.request.AnswerRequest;
import ir.aliloc.api.core.models.response.QuestionAnswerResponse;
import ir.aliloc.api.core.quize.IQuizService;
import ir.aliloc.api.core.quize.Quiz;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.ws.rs.ForbiddenException;
import java.lang.reflect.Type;
import java.util.*;

@Service
@Transactional
class QuestionService implements IQuestionService {

    @Autowired
    private IQuizService mIQuizService;
    @Autowired
    private IQuestionDAO mIQuestionDAO;
    @Autowired
    private IQuestionOptionService mIQuestionOptionService;
    @Autowired
    private ModelMapper mModelMapper;
    @Autowired
    private IQuestionBankQuestionService mIQuestionBankQuestionService;
    @Autowired
    private IUserQuizService mIUserQuizService;
    @Autowired
    private IAuthenticationFaced mIAuthenticationFaced;
    @Autowired
    private IUserCourseService mIUserCourseService;
    @Autowired
    private IUserStepService mIUserStepService;
    @Autowired
    private ICertificateService mICertificateService;


    @Override
    public List<QuestionAnswerDTO> getListOfQuestionWithAnswer(List<Long> questionIds) throws Exception {
        List<Question> questionList = mIQuestionDAO.getQuestionListById(questionIds);
        Type questionDTOType = new TypeToken<List<QuestionAnswerDTO>>() {
        }.getType();
        return mModelMapper.map(questionList, questionDTOType);
    }


    private List<QuestionOption> getMapOptionsWithAnswer(List<Long> questionIds) throws Exception {
        List<QuestionOption> options = mIQuestionDAO.getOptionListById(questionIds);
        return options;
    }

    @Override
    public List<QuestionDTO> getListOfQuestion(long quizId) throws Exception {
        quizCheking(quizId);
        Random random = new Random();
        Quiz quiz = mIQuizService.getQuizMainObject(quizId);
        List<Long> questionIds = mIQuestionBankQuestionService.getQuestionIds(quiz.getQuestionBank().getId());
        List<Long> randomIds = new ArrayList<>();
        for (int i = 0; i < quiz.getQuestionCount(); i++) {
            int randomIndex = random.nextInt(questionIds.size());
            randomIds.add(questionIds.get(randomIndex));
            questionIds.remove(randomIndex);
        }
        List<Question> questions = mIQuestionDAO.getQuestionListById(randomIds);
        Type questionDTOType = new TypeToken<List<QuestionDTO>>() {
        }.getType();
        return mModelMapper.map(questions, questionDTOType);
    }

    private void quizCheking(long quizId) throws Exception {
        long userId = Long.parseLong(mIAuthenticationFaced.getAuthentication().getName());
        UserQuiz userQuiz = mIUserQuizService.getCustomUserQuizByUserId(userId, quizId);
        if (userQuiz != null) {
            if (userQuiz.getEType() == EQuizResult.PASS) {
                throw new ForbiddenException(MessageConstant.ERROR_USER_PASS_QUIZ);
            }
        }
    }

    @Override
    public QuestionAnswerResponse checkAnswer(AnswerRequest answerRequest) throws Exception {
        long userId = Long.parseLong(mIAuthenticationFaced.getAuthentication().getName());
        Quiz quiz = mIQuizService.getQuizMainObject(answerRequest.getQuizId());
        List<Long> questionIds = new ArrayList<>();
        for (AnswerInitRequest answerInitRequest : answerRequest.getAnswerList()) {
            questionIds.add(answerInitRequest.getQuestionId());
        }
        List<QuestionOption> options = getMapOptionsWithAnswer(questionIds);
        List<AnswerInitRequest> wrongPointList = new ArrayList<>();
        Map<Long, QuestionOption> answer = new HashMap<>();
        for (QuestionOption questionOption : options) {
            answer.put(questionOption.getQuestion().getId(), questionOption);
        }
        int rightAnswerPoint = 0;
        int wrongAnswerPoint = 0;

        for (AnswerInitRequest answerInitRequest : answerRequest.getAnswerList()) {
            QuestionOption option = answer.get(answerInitRequest.getQuestionId());
            if (answerInitRequest.getAnswerId() == option.getId()) {
                rightAnswerPoint++;
            } else {
                AnswerInitRequest wrongAnswerInitRequest = new AnswerInitRequest(option.getQuestion().getId(), option.getId());
                wrongPointList.add(wrongAnswerInitRequest);
                wrongAnswerPoint++;
            }
        }

        EQuizResult quizResult;
        if (rightAnswerPoint >= quiz.getPassPoint()) {
            quizResult = EQuizResult.PASS;
        } else {
            quizResult = EQuizResult.FAIL;
        }
        mIUserQuizService.addQuizUser(userId, quiz.getId(), rightAnswerPoint, quizResult);
        if (answerRequest.getStepId() == 0) {
            mICertificateService.saveCertificate(mICertificateService.createCertificate(userId, answerRequest.getCourseId()));
        } else {
            mIUserStepService.addUserStep(answerRequest.getStepId(), userId);
        }
        QuestionAnswerResponse questionAnswerResponse = new QuestionAnswerResponse();
        questionAnswerResponse.setQuizResult(quizResult);
        questionAnswerResponse.setRightPoint(rightAnswerPoint);
        questionAnswerResponse.setWrongPoint(wrongAnswerPoint);
        questionAnswerResponse.setWrongPointQuestions(wrongPointList);
        return questionAnswerResponse;
    }
}
