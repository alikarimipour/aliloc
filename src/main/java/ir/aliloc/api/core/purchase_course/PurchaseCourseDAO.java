/**
 * 1/1/2019
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.purchase_course;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
class PurchaseCourseDAO implements IPurchaseCourseDAO {
    @Autowired
    private SessionFactory mSessionFactory;

    @Override
    public void addPurchaseCourse(PurchaseCourse purchaseCourse) throws Exception {
        mSessionFactory.getCurrentSession().save(purchaseCourse);
    }

    @Override
    public PurchaseCourse getPurchaseCourse(long userId, long courseId) throws Exception {
        Query query = mSessionFactory.getCurrentSession().createQuery("from PurchaseCourse where course.id=:courseId and user.id=:userId");
        query.setParameter("courseId", courseId);
        query.setParameter("userId", userId);
        List result = query.list();
        if (result.size() != 0) {
            return (PurchaseCourse) result.get(0);
        }
        return null;
    }
}
