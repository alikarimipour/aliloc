/**
 * 3/7/2018
 * author: alikarimipour157@gmail.com
 */
package ir.aliloc.api.core.authentication;


import ir.aliloc.api.core.user.models.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.ws.rs.NotFoundException;
import java.util.*;

@Repository
class AuthDAO implements IAuthDAO<Auth> {

    @Autowired
    private SessionFactory mSessionFactory;

    @Override
    public Auth addAuth(Auth auth) throws Exception {
        Session session = mSessionFactory.getCurrentSession();
        User user = session.load(User.class, auth.getUser().getId());
        auth.setUser(user);
        auth.setLastModify(new Date().getTime());
        session.save(auth);
        session.flush();
        session.clear();
        session.evict(auth);
        return auth;
    }

    @Override
    public void updateAuth(Auth entity) throws Exception {
        Session session = mSessionFactory.getCurrentSession();
        Query query = session.createQuery("update Auth set token=:token,verifyCode=:verifyCode where user.id=:userId");
        query.setParameter("token", entity.getToken());
        query.setParameter("verifyCode", entity.getVerifyCode());
        query.setParameter("userId", entity.getUser().getId());
        query.executeUpdate();
    }

    @Override
    public void deleteAuth(String entity) throws Exception {
        Session session = mSessionFactory.getCurrentSession();
        Query query = session.createQuery("delete Auth where token = :token");
        query.setParameter("token", entity);
        query.executeUpdate();
    }

    @Override
    public Auth getAuth(String token) throws NotFoundException {
        Session session = mSessionFactory.getCurrentSession();
        Query query = session.createQuery("from Auth where token = :token");
        query.setParameter("token", token);
        if (query.list().size() == 0) {
            throw new NotFoundException();
        } else {
            return (Auth) query.list().get(0);
        }
    }

    @Override
    public boolean exist(long userId) throws Exception {
        Session session = mSessionFactory.getCurrentSession();
        Query query = session.createQuery("from Auth where user.id = :userId");
        query.setParameter("userId", userId);
        return query.list().size() != 0;
    }

    @Override
    public long updateVerifyCode(int verifyCode, long userId) throws Exception {
        Session session = mSessionFactory.getCurrentSession();
        Query firstQuery = session.createQuery("select auth.id from Auth as auth where auth.user.id = :userId order by lastModify desc");
        firstQuery.setParameter("userId", userId);
        firstQuery.setMaxResults(1);
        long authId = (long) firstQuery.uniqueResult();
        Query query = session.createQuery("update Auth set verifyCode = :verifyCode,lastModify =:time where id =:authId");
        query.setParameter("time", Calendar.getInstance(TimeZone.getTimeZone("Asia/Tehran")).getTimeInMillis());
        query.setParameter("verifyCode", verifyCode);
        query.setParameter("authId", authId);
        query.executeUpdate();
        return authId;
    }

    @Override
    public Auth updateTokenById(long id, String token) throws Exception {
        Session session = mSessionFactory.getCurrentSession();
        Query query = session.createQuery("update Auth set token = :token,lastModify = :time where id = :id");
        query.setParameter("time", Calendar.getInstance(TimeZone.getTimeZone("Asia/Tehran")).getTimeInMillis());
        query.setParameter("token", token);
        query.setParameter("id", id);
        query.executeUpdate();
        Query getQuery = session.createQuery("from Auth where id = :id");
        getQuery.setParameter("id", id);
        return (Auth) getQuery.uniqueResult();
    }


    @Override
    public List<Auth> addMembers(List<Auth> authList) throws Exception {
        Session session = mSessionFactory.getCurrentSession();
        List<Auth> newAuth = new ArrayList<>();
        Auth initAuth = null;
        for (Auth auth : authList) {
//            List initAuthList = (List) session.createQuery(Auth.class, "auth").add(Restrictions.eq("auth.user.id", auth.getUser().getId())).list();
            List initAuthList = (List) session.createQuery("from Auth where user.id = :userId").setParameter("userId", auth.getUser().getId()).list();
            if (initAuthList.size() != 0) {
                initAuth = (Auth) initAuthList.get(0);
            }
            if (initAuth == null) {
                session.save(auth);
                session.flush();
                session.clear();
                session.evict(auth);
            } else {
                initAuth.setLastModify(auth.getLastModify());
                initAuth.setVerifyCode(auth.getVerifyCode());
                session.update(initAuth);
                auth.setToken(initAuth.getToken());
                auth.setId(initAuth.getId());
            }
            newAuth.add(auth);
        }
        return newAuth;
    }

    @Override
    public Auth getAuthById(long authId) throws Exception {
        Session session = mSessionFactory.getCurrentSession();
        Query query = session.createQuery("from Auth where id=:id");
        query.setParameter("id", authId);
        if (query.list().size() != 0) {
            return (Auth) query.list().get(0);
        }
        return null;
    }

    @Override
    public void updateTokenByUserId(long userId, String token) throws Exception {
        Query query = mSessionFactory.getCurrentSession().createQuery("update Auth set token=:token,lastModify=:time where user.id=:userId");
        query.setParameter("userId", userId);
        query.setParameter("token", token);
        query.setParameter("time", Calendar.getInstance(TimeZone.getTimeZone("Asia/Tehran")).getTimeInMillis());
        query.executeUpdate();
    }

    @Override
    public Auth getAuthByMobile(String mobile) throws Exception {
        Query query = mSessionFactory.getCurrentSession().createQuery("from Auth where user.mobile = :mobile");
        query.setParameter("mobile",mobile);
        return (Auth) query.uniqueResult();
    }


    @Override
    public Auth getAuthByUserId(long userId) throws Exception {
        Session session = mSessionFactory.getCurrentSession();
        Query query = session.createQuery("from Auth where user.id = :id");
        query.setParameter("id", userId);
        if (query.list().size() == 0) {
            return null;
        }
        return (Auth) query.list().get(0);
    }
}
