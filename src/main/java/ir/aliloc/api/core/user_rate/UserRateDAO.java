/**
 * 12/2/2018
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.user_rate;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
class UserRateDAO implements IUserRateDAO {

    @Autowired
    private SessionFactory mSessionFactory;

    @Override
    public List<UserRate> getHistoryOfRateByUser(long userId, int offset, int size) throws Exception {
        Query query = mSessionFactory.getCurrentSession().createQuery("from UserRate where user.id=:userId");
        query.setParameter("userId", userId);
        query.setFirstResult(offset).setMaxResults(size);
        return query.list();
    }

    @Override
    public void addUserRate(UserRate userRate) throws Exception {
        mSessionFactory.getCurrentSession().save(userRate);
    }

    @Override
    public List<Object> checkUserRateByType(RateType rateType, long userId) throws Exception {
        String sqlQuery = "select ur.id as id,ur.description as description,ur.rate as rate,ur.time as time ,ur.user_id as userId," +
                " rm.id as modelId,rm.description as modelDescription,rm.maxCount as maxCount,rm.periodically as periodically,rm.rate as rate,rm.type as type , rm.text as text, \n" +
                " count(*) as count from user_rate as ur " +
                " left join rateModel as rm on rm.id = ur.model_id " +
                " where ur.id =:userId and rm.type=:rateType  group by ur.user_id order by time desc limit 1";
        SQLQuery query = mSessionFactory.getCurrentSession()
                .createSQLQuery(sqlQuery);
        query.setParameter("rateType", rateType);
        query.setParameter("userId", userId);
        return query.list();
    }

    @Override
    public boolean checkInvitationUserRate(String invitation, RateType rateType, long userId) throws Exception {
        Query query = mSessionFactory.getCurrentSession().createQuery("from UserRate where user.id=:userId and rateModel.rateType=:rateType and description=:invitationCode");
        query.setParameter("userId", userId);
        query.setParameter("rateType", rateType);
        query.setParameter("invitationCode", invitation);
        List result = query.list();
        return result.size() != 0;
    }

    @Override
    public boolean checkRegistrationUserRate(long userId) throws Exception {
        Query query = mSessionFactory.getCurrentSession().createQuery("from UserRate where user.id=:userId and rateModel.rateType=:rateType");
        query.setParameter("rateType", RateType.REGISTER);
        query.setParameter("userId", userId);
        List result = query.list();
        return result.size() != 0;
    }
}
