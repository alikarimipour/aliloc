/**
 * 12/31/2018
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.user_step;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
class UserStepDAO implements IUserStepDAO {

    @Autowired
    private SessionFactory mSessionFactory;

    @Override
    public void addUserStep(UserStep userStep) throws Exception {
        mSessionFactory.getCurrentSession().save(userStep);
    }

    @Override
    public UserStep getUserStep(long userId, long stepId) throws Exception {
        Query query = mSessionFactory.getCurrentSession().createQuery("from UserStep where user.id=:userId and step.id=:stepId");
        query.setParameter("userId", userId);
        query.setParameter("stepId", stepId);
        List result = query.list();
        if (result.size() != 0) {
            return (UserStep) result.get(0);
        }
        return null;
    }

    @Override
    public List<Long> getUserStepIds(long userId) throws Exception {
        Query query = mSessionFactory.getCurrentSession().createQuery("select us.step.id from UserStep as us where us.user.id=:userId");
        query.setParameter("userId",userId);
        return query.list();
    }
}
