/**
 * 12/15/2018
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.payment;

interface IPaymentDAO {

    void addPayment(Payment payment) throws Exception;

    Payment loadPayment(String authority) throws Exception;

    void updatePayment(Payment payment) throws Exception;
}
