/**
 * 11/14/2018
 * author: alikarimipour157@gmail.com
 */
package ir.aliloc.api.core.multimedia;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
class MultiMediaDAO implements IMultiMediaDAO{

    @Autowired
    private SessionFactory mSessionFactory;

    @Override
    public MultiMedia addProfile(MultiMedia multiMedia) throws Exception {
        long multimediaId = (long) mSessionFactory.getCurrentSession().save(multiMedia);
        return mSessionFactory.getCurrentSession().get(MultiMedia.class,multimediaId);
    }

    @Override
    public MultiMedia getMultiMediaByAddressId(long multimediaAddressId) throws Exception {
//        Query query = mSessionFactory.getCurrentSession().createQuery("from MultiMedia ")
        return null;
    }

}
