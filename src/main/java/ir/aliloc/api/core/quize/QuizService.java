/**
 * 12/17/2018
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.quize;

import ir.aliloc.api.core.util.CustomMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
class QuizService implements IQuizService {

    @Autowired
    private IQuizDAO mIQuizDAO;
    @Autowired
    private CustomMapperService mCustomMapperService;

    @Override
    public QuizDTO getQuizById(long quizId) throws Exception {
        Quiz quiz = mIQuizDAO.getQuizById(quizId);
        return mCustomMapperService.quizToQuizDTO(quiz);
    }

    @Override
    public Quiz getQuizMainObject(long quizId) throws Exception {
        return mIQuizDAO.getQuizById(quizId);
    }
}
