/**
 * 12/15/2018
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.payment;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
class PaymentDAO implements IPaymentDAO {
    @Autowired
    private SessionFactory mSessionFactory;

    @Override
    public void addPayment(Payment payment) throws Exception {
        mSessionFactory.getCurrentSession().save(payment);
    }

    @Override
    public void updatePayment(Payment payment) throws Exception {
        mSessionFactory.getCurrentSession().update(payment);
    }

    @Override
    public Payment loadPayment(String authority) throws Exception {
        Query query = mSessionFactory.getCurrentSession().createQuery("from Payment p where p.payKey=:authority");
        query.setParameter("authority", authority);
        return (Payment) query.uniqueResult();
    }

}
