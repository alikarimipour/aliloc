/**
 * 12/25/2018
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.user_lecture;

import ir.aliloc.api.core.lecture.Lecture;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
class UserLectureDAO implements IUserLectureDAO {

    @Autowired
    private SessionFactory mSessionFactory;


    @Override
    public void addUserLecture(UserLecture userLecture) throws Exception {
        mSessionFactory.getCurrentSession().save(userLecture);
    }

    @Override
    public boolean checkUserLecture(long lectureId, long userId) throws Exception {
        Query query = mSessionFactory.getCurrentSession().createQuery("from UserLecture where lecture.id=:lectureId and user.id=:userId");
        query.setParameter("userId", userId);
        query.setParameter("lectureId", lectureId);
        List result = query.list();
        return result.size() != 0;
    }

    @Override
    public List<Lecture> getUserLectureByUserId(long userId) throws Exception {
        Query query = mSessionFactory.getCurrentSession().createQuery("select ul.lecture from UserLecture as ul where ul.user.id=:userId order by ul.lecture.title ");
        query.setParameter("userId",userId);
        return query.list();
    }

    @Override
    public List<Long> getDownloadLectureIdByUserId(long userId) throws Exception {
        Query query = mSessionFactory.getCurrentSession().createQuery("select ul.lecture.id from UserLecture as ul where ul.user.id=:userId");
        query.setParameter("userId",userId);
        return query.list();
    }
}
