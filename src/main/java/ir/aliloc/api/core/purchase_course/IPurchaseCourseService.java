/**
 * 1/1/2019
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.purchase_course;

public interface IPurchaseCourseService {

    boolean checkPurchaseCourse(long userId, long courseId) throws Exception;

    void addCoursePurchase(PurchaseCourse purchaseCourse) throws Exception;
}
