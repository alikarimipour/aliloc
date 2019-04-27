/**
 * 12/17/2018
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.question_option;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
class QuestionOptionService implements IQuestionOptionService {

    @Autowired
    private IQuestionOptionDAO mIQuestionOptionDAO;

    @Override
    public List<QuestionOption> getMainObjectByQuestionIds(List<Long> questionIds) throws Exception {
        return mIQuestionOptionDAO.getQuestionOptionByQuestionIds(questionIds);
    }
}
