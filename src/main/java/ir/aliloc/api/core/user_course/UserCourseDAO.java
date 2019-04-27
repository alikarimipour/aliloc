/**
 * 12/31/2018
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.user_course;

import ir.aliloc.api.core.course.Course;
import ir.aliloc.api.core.enums.ECourseTypeEnum;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
class UserCourseDAO implements IUserCourseDAO {

    @Autowired
    private SessionFactory mSessionFactory;

    @Override
    public List<Course> getAllHistoryCourse(long userId) throws Exception {
        Query query = mSessionFactory.getCurrentSession().createQuery("select uc.course from UserCourse as uc where uc.user.id=:userId order by uc.time desc ");
        query.setParameter("userId", userId);
        return query.list();
    }

    @Override
    public List<UserCourse> getAllHistoryUserCourse(long userId) throws Exception {
        Query query = mSessionFactory.getCurrentSession().createQuery("from UserCourse as uc where uc.user.id=:userId order by uc.time desc ");
        query.setParameter("userId", userId);
        return query.list();
    }

    @Override
    public UserCourse getMainObjectUserCourse(long userId, long courseId) throws Exception {
        Query query = mSessionFactory.getCurrentSession().createQuery("from UserCourse where course.id=:courseId and user.id=:userId");
        query.setParameter("userId", userId);
        query.setParameter("courseId", courseId);
        List result = query.list();
        if (result.size() != 0) {
            UserCourse userCourse = (UserCourse) result.get(0);
            Hibernate.initialize(userCourse.getCourse());
            return userCourse;
        }
        return null;
    }

    @Override
    public void addUserCourse(UserCourse userCourse) throws Exception {
        mSessionFactory.getCurrentSession().save(userCourse);
    }

    @Override
    public void updateUserCourse(UserCourse userCourse) throws Exception {
        mSessionFactory.getCurrentSession().update(userCourse);
    }

    @Override
    public List<Course> getHistoryOfUserCourse(long userId, ECourseTypeEnum eCourseTypeEnum) throws Exception {
        Query query = mSessionFactory.getCurrentSession().createQuery("select uc.course from UserCourse as uc where uc.user.id=:userId and uc.course.courseCategory.eCourseTypeEnum=:courseType order by uc.time desc ");
        query.setParameter("userId", userId);
        query.setParameter("courseType", eCourseTypeEnum);
        return query.list();
    }

}
