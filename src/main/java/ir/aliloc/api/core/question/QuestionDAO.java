/**
 * 12/17/2018
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.question;

import ir.aliloc.api.core.question_option.QuestionOption;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
class QuestionDAO implements IQuestionDAO {

    @Autowired
    private SessionFactory mSessionFactory;


    @Override
    public List<Long> getQuestionIds(long bookId, long bookIndexId) throws Exception {
        Query query = mSessionFactory.getCurrentSession().createQuery("select q.id from Question as q where q.book.id=:bookId and bookIndex.id=:bookIndex and q.active=true ");
        query.setParameter("bookId", bookId);
        query.setParameter("bookIndex", bookIndexId);
        return query.list();
    }

    @Override
    public List<Question> getQuestionListById(List<Long> questionIds) throws Exception {
        Query query = mSessionFactory.getCurrentSession().createQuery("from Question where id in (:ids)");
        query.setParameterList("ids", questionIds);
        return query.list();
    }

    @Override
    public List<QuestionOption> getOptionListById(List<Long> optionList) throws Exception {
        Query query = mSessionFactory.getCurrentSession().createQuery(" from QuestionOption where question.id in (:ids) and isAnswer=true");
        query.setParameterList("ids", optionList);
        return query.list();
    }
}
