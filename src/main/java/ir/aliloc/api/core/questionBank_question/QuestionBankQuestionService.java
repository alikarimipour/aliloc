/**
 * 1/6/2019
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.questionBank_question;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
class QuestionBankQuestionService implements IQuestionBankQuestionService {
    @Autowired
    private IQuestionBankQuestionDAO mIQuestionBankQuestionDAO;

    @Override
    public List<Long> getQuestionIds(long questionBankId) throws Exception {
        return mIQuestionBankQuestionDAO.getQuestionIds(questionBankId);
    }
}
