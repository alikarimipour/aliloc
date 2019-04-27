/**
 * 12/31/2018
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.user_quize;

import ir.aliloc.api.security.IAuthenticationFaced;
import ir.aliloc.api.core.enums.EQuizResult;
import ir.aliloc.api.core.quize.Quiz;
import ir.aliloc.api.core.user.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.List;

@Service
@Transactional
class UserQuizService implements IUserQuizService {

    @Autowired
    private IUserQuizDAO mIUserQuizDAO;
    @Autowired
    private IAuthenticationFaced mIAuthenticationFaced;

    @Override
    public List<UserQuizDTO> getHistoryOfUserQuiz(int offset, int size) throws Exception {
        long userId = Long.parseLong(mIAuthenticationFaced.getAuthentication().getName());
        List<UserQuiz> userQuizList = mIUserQuizDAO.getHistoryOfQuiz(userId, offset, size);
        return null;
    }

    @Override
    public void addQuizUser(long userId, long quizId, int rightAnswerCount, EQuizResult eQuizResult) throws Exception {
        User user = new User();
        user.setId(userId);
        Quiz quiz = new Quiz();
        quiz.setId(quizId);
        UserQuiz userQuiz = new UserQuiz();
        userQuiz.setPoint(rightAnswerCount);
        userQuiz.setEType(eQuizResult);
        userQuiz.setQuiz(quiz);
        userQuiz.setUser(user);
        userQuiz.setTime(Calendar.getInstance().getTimeInMillis());
        mIUserQuizDAO.addUserQuiz(userQuiz);
    }

    @Override
        public UserQuiz getCustomUserQuizByUserId(long userId, long quizId) throws Exception {
        return mIUserQuizDAO.getCustomUserQuizByUserId(userId, quizId);
    }
}
