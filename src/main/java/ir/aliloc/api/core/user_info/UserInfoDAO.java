/**
 * 10/8/2018
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.user_info;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
class UserInfoDAO implements IUserInfoDAO {

    @Autowired
    private SessionFactory mSessionFactory;

    @Override
    public void addUserInfo(UserInfo userInfo) throws Exception {
        Session session = mSessionFactory.getCurrentSession();
        session.save(userInfo);
    }


}
