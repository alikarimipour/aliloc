/**
 * 3/7/2018
 * author: alikarimipour157@gmail.com
 */
package ir.aliloc.api.core.user;


import ir.aliloc.api.core.multimedia.MimeType;
import ir.aliloc.api.core.multimedia.MultiMediaDTO;
import ir.aliloc.api.core.user.models.User;
import ir.aliloc.api.core.user.models.UserRateDTO;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Repository
class UserDAO implements IUserDAO<User> {

    @Autowired
    private SessionFactory mSessionFactory;

    @Override
    public User addUser(User user) throws Exception {
        Session session = mSessionFactory.getCurrentSession();
        long id = (long) session.save(user);
//        user.setId(id);
        session.flush();
        session.clear();
        session.evict(user);
        return user;
    }

    @Override
    public void updateUser(User entity) throws Exception {
        String queryString = "update User set firstName=:firstName,lastName=:lastName,birthDate=:birthDate,nationalCode=:nationalCode,gender=:genderType,fatherName=:fatherName";
        if (entity.getAvatar() != null) {
            queryString += ",avatar=:avatar";
        }
        queryString += " where id=:userId";
        Query query = mSessionFactory.getCurrentSession().createQuery(queryString);
        query.setParameter("userId", entity.getId());
        query.setParameter("firstName", entity.getFirstName());
        query.setParameter("birthDate", entity.getBirthDate());
        query.setParameter("lastName", entity.getLastName());
        query.setParameter("nationalCode", entity.getNationalCode());
        query.setParameter("genderType", entity.getGender());
        query.setParameter("fatherName", entity.getFatherName());
        if (entity.getAvatar() != null) {
            query.setParameter("avatar", entity.getAvatar());
        }
        query.executeUpdate();
    }

    @Override
    public void deleteUser(String mobile) throws Exception {

    }

    @Override
    public User getUserByMobile(String mobile) throws Exception {
        Session session = mSessionFactory.getCurrentSession();
        Query query = session.createQuery("from User where mobile = :mobile");
        query.setParameter("mobile", mobile);
        List result = query.list();
        if (result.size() == 0) {
            return null;
        }
        @SuppressWarnings("unchecked")
        User user = (User) result.get(0);
        return user;
    }

    @Override
    public User getUserById(long userId) throws Exception {
        Session session = mSessionFactory.getCurrentSession();
        User user = session.load(User.class, userId);
        return user;
    }

    @Override
    public boolean exist(String mobile) throws Exception {
        Session session = mSessionFactory.getCurrentSession();
        Query query = session.createQuery("from User where mobile=:mobile");
        query.setParameter("mobile", mobile);
        return query.list().size() != 0;
    }

    @Override
    public List<User> popularUsers() throws Exception {
        return null;
    }


    @Override
    public List<User> getUserDetailsListFromMobile(List<User> userList) throws Exception {
        Session session = mSessionFactory.getCurrentSession();
        List<User> updateUser = new ArrayList<>();
        for (User user : userList) {
            List userInfos = session.createCriteria(User.class).add(Restrictions.eq("mobile", user.getMobile())).list();
            if (userInfos.size() != 0) {
                updateUser.add((User) userInfos.get(0));
            }
        }
        return updateUser;
    }


    @Override
    public void updatePasswordById(long userId, String password) throws Exception {
        Query query = mSessionFactory.getCurrentSession().createQuery("update User set password=:password where id = :userId");
        query.setParameter("password", password);
        query.setParameter("userId", userId);
        query.executeUpdate();
    }

    @Override
    public UserRateDTO getUserByRate(long userId) throws Exception {
        String query = "select u.id,u.birthDate,u.creationDate,u.invitationCode,u.lastName,u.firstName,u.nationalCode,u.gender,u.fatherName,m.id as multimediaId,m.url,m.mime,m.quality,m.description," +
                " (select sum(ur.rate) from user_rate as ur where ur.user_id=:userId ) as totalCount ," +
                " (select sum(ur.rate) from user_rate as ur where ur.user_id=:userId and :fromTime<= ur.time <=:toTime)as monthCount" +
                " from user as u" +
                " left join multimedia as m on m.id = u.avatar_multimedia_id " +
                " where u.id =:userId";
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, -1);
        long oneMonthAgo = c.getTimeInMillis();
        long currentTime = Calendar.getInstance().getTimeInMillis();
        SQLQuery sqlQuery = mSessionFactory.getCurrentSession().createSQLQuery(query);
        sqlQuery.setParameter("userId", userId);
        sqlQuery.setParameter("fromTime", oneMonthAgo);
        sqlQuery.setParameter("toTime", currentTime);
        List<Object[]> results = sqlQuery.list();
        UserRateDTO userRateDTO = new UserRateDTO();
        results.forEach((record) -> {
            userRateDTO.setId(((BigInteger) record[0]).longValue());
            userRateDTO.setBirthDate(((BigInteger) record[1]).longValue());
            userRateDTO.setCreationDate(((BigInteger) record[2]).longValue());
            userRateDTO.setInvitationCode((String) record[3]);
            userRateDTO.setLastName((String) record[4]);
            userRateDTO.setFirstName((String) record[5]);
            userRateDTO.setNationalCode((String) record[6]);
            if (record[7] != null) {
                userRateDTO.setGender(GenderType.values()[(Integer) record[7]]);
            }
            userRateDTO.setFatherName((String) record[8]);
            if (record[9] != null) {
                MultiMediaDTO multiMediaDTO = new MultiMediaDTO();
                multiMediaDTO.setId(((BigInteger) record[9]).longValue());
                multiMediaDTO.setUrl((String) record[10]);
                multiMediaDTO.setMime(MimeType.values()[(Integer) record[11]]);
                multiMediaDTO.setQuality((String) record[12]);
                multiMediaDTO.setDescription((String) record[13]);
                userRateDTO.setAvatar(multiMediaDTO);
            }
            if (record[14] != null) {
                userRateDTO.setTotalRate(((BigDecimal) record[14]).intValue());
            }
            if (record[15] != null) {
                userRateDTO.setMonthRate(((BigDecimal) record[15]).intValue());
            }
        });
        return userRateDTO;
    }

    @Override
    public boolean checkInvitationCode(String invitationCode) throws Exception {
        Query query = mSessionFactory.getCurrentSession().createQuery("from User where invitationCode=:code");
        query.setParameter("code", invitationCode);
        List result = query.list();
        return result.size() != 0;
    }

    @Override
    public User getLoadObject(long userId) throws Exception {
        return mSessionFactory.getCurrentSession().load(User.class, userId);
    }

    @Override
    public Long getUserIdByInvitationCode(String invitationCode) throws Exception {
        Query query = mSessionFactory.getCurrentSession().createQuery("select id from User where invitationCode=:inviteCode");
        query.setParameter("inviteCode", invitationCode);
        List result = query.list();
        if (result.size() != 0) {
            return (Long) result.get(0);
        }
        return null;
    }


}
