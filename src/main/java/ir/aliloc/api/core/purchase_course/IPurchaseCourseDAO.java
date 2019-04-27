/**
 * 1/1/2019
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.purchase_course;

interface IPurchaseCourseDAO {

    void addPurchaseCourse(PurchaseCourse purchaseCourse) throws Exception;

    PurchaseCourse getPurchaseCourse(long userId, long courseId) throws Exception;

}
