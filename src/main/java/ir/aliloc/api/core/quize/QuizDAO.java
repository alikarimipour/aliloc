/**
 * 12/17/2018
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.quize;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
class QuizDAO implements IQuizDAO {

    @Autowired
    private SessionFactory mSessionFactory;


    @Override
    public Quiz getQuizById(long quizId) throws Exception {
        Query query = mSessionFactory.getCurrentSession().createQuery("from Quiz where id=:quizId");
        query.setParameter("quizId", quizId);
        return (Quiz) query.uniqueResult();
    }
}
