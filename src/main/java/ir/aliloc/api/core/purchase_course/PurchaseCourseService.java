/**
 * 1/1/2019
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.purchase_course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
class PurchaseCourseService implements IPurchaseCourseService {
    @Autowired
    private IPurchaseCourseDAO mIPurchaseCourseDAO;


    @Override
    public boolean checkPurchaseCourse(long userId, long courseId) throws Exception {
        PurchaseCourse purchaseCourse = mIPurchaseCourseDAO.getPurchaseCourse(userId, courseId);
        return purchaseCourse != null;
    }


    @Override
    public void addCoursePurchase(PurchaseCourse purchaseCourse) throws Exception {
        mIPurchaseCourseDAO.addPurchaseCourse(purchaseCourse);
    }
}
