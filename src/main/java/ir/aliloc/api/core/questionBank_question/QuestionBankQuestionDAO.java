/**
 * 1/6/2019
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.questionBank_question;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
class QuestionBankQuestionDAO implements IQuestionBankQuestionDAO {
    @Autowired
    private SessionFactory mSessionFactory;

    @Override
    public List<Long> getQuestionIds(long questionBankId) throws Exception {
        Query query = mSessionFactory.getCurrentSession().createQuery("select qbq.question.id from QuestionBankQuestion as qbq where qbq.questionBank.id=:questionBankId and qbq.questionBank.isActive=true");
        query.setParameter("questionBankId", questionBankId);
        return query.list();
    }
}
