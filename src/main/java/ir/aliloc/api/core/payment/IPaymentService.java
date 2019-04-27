/**
 * 12/15/2018
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.payment;

import ir.aliloc.api.core.course.CourseDTO;
import ir.aliloc.api.core.models.request.BookIdRequest;

public interface IPaymentService {

    void savePayment(PaymentDTO paymentDTO) throws Exception;

    boolean checkEcoursPaymentType(CourseDTO courseDTO) throws Exception;

    PaymentDTO paymentZarinBookPallStepOne(BookIdRequest bookIdRequest) throws Exception;

    PaymentDTO paymentZarinPallCourseStepOne(CourseDTO courseDTO) throws Exception;

    Payment loadPaymentByAuthority(String authority) throws Exception;

    void acceptPaymentStepTwo(Payment payment) throws Exception;

    void updatePayment(Payment payment) throws Exception;
}
