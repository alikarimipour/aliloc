/**
 * 12/17/2018
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.cours_category;

import ir.aliloc.api.core.enums.ECourseTypeEnum;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
class CourseCategoryDAO implements ICourseCategoryDAO {

    @Autowired
    private SessionFactory mSessionFactory;


    @Override
    public List<CourseCategory> getAllCourseCategory() {
        Query query = mSessionFactory.getCurrentSession().createQuery("from CourseCategory where isActive=true ");
        return query.list();
    }

    @Override
    public List<CourseCategory> getAllCourseCategoryByType(ECourseTypeEnum courseTypeEnum) {
        Query query = mSessionFactory.getCurrentSession().createQuery("from CourseCategory as cc where isActive=true and cc.eCourseTypeEnum=:type");
        query.setParameter("type",courseTypeEnum);
        return query.list();
    }
}
