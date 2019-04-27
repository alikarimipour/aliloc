/**
 * 12/31/2018
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.certificate;

import ir.aliloc.api.config.MessageConstant;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.ws.rs.ForbiddenException;
import java.util.List;

@Repository
class CertificateDAO implements ICertificateDAO {

    @Autowired
    private SessionFactory mSessionFactory;

    @Override
    public void addCertificate(Certificate certificate) throws Exception {
        mSessionFactory.getCurrentSession().save(certificate);
    }

    @Override
    public List<Certificate> getCertificateHistory(long userId) {
        Query query = mSessionFactory.getCurrentSession().createQuery("from Certificate where user.id=:userId order by time desc ");
        query.setParameter("userId", userId);
        return query.list();
    }

    @Override
    public Certificate getCertificate(long userId, Long courseId) {
        Query query = mSessionFactory.getCurrentSession().createQuery("from Certificate where user.id=:userId and course.id=:courseId ");
        query.setParameter("userId", userId);
        query.setParameter("courseId", courseId);
        List list = query.list();
        if (list.size()==1){
            return (Certificate) query.uniqueResult();
        }else {
            throw new ForbiddenException(MessageConstant.ERROR_MORE_THAN_ONE_CERTIFICATE);
        }
    }
}
