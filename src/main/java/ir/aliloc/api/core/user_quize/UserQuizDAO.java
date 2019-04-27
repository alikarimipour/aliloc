/**
 * 12/31/2018
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.user_quize;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
class UserQuizDAO implements IUserQuizDAO {

    @Autowired
    private SessionFactory mSessionFactory;

    @Override
    public List<UserQuiz> getHistoryOfQuiz(long userId, int offset, int size) throws Exception {
        Query query = mSessionFactory.getCurrentSession().createQuery("from UserQuiz where user.id=:userId order by time desc ");
        query.setParameter("userId", userId);
        query.setFirstResult(offset).setMaxResults(size);
        return query.list();
    }

    @Override
    public void addUserQuiz(UserQuiz userQuiz) throws Exception {
        mSessionFactory.getCurrentSession().save(userQuiz);
    }

    @Override
    public UserQuiz getCustomUserQuizByUserId(long userId, long quizId) throws Exception {
        Query query = mSessionFactory.getCurrentSession().createQuery(" from UserQuiz as uq where uq.user.id=:userId and uq.quiz.id=:quizId order by time desc");
        query.setParameter("userId", userId);
        query.setParameter("quizId", quizId);
        List<UserQuiz> list = query.list();
        if (list.size()!=0){
            return list.get(0);
        }else {
            return null;
        }
    }
}
