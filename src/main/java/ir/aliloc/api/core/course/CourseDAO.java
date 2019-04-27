/**
 * 12/17/2018
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.course;

import ir.aliloc.api.core.enums.ECourseTypeEnum;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
class CourseDAO implements ICourseDAO {

    @Autowired
    private SessionFactory mSessionFactory;

    @Override
    public List<Course> getCourseByType(ECourseTypeEnum courseTypeEnum, int offset, int size) throws Exception {
        Query query = mSessionFactory.getCurrentSession().createQuery("from Course where courseCategory.eCourseTypeEnum=:courseType and isActive=true");
        query.setParameter("courseType", courseTypeEnum);
        query.setFirstResult(offset).setMaxResults(size);
        return query.list();
    }

    @Override
    public List<Course> getCourseByCategoryId(long categoryId, int offset, int size) throws Exception {
        Query query = mSessionFactory.getCurrentSession().createQuery("from Course where courseCategory.id=:categoryId and isActive=true");
        query.setParameter("categoryId", categoryId);
        query.setFirstResult(offset).setMaxResults(size);
        return query.list();
    }

    @Override
    public Course getCourseById(long courseId) throws Exception {
        Query query = mSessionFactory.getCurrentSession().createQuery("from Course where id=:courseId");
        query.setParameter("courseId",courseId);
        return (Course) query.uniqueResult();
    }
}
