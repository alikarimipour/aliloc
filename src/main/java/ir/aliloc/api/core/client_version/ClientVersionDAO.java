/**
 * 10/9/2018
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.client_version;


import ir.aliloc.api.core.enums.EOS;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
class ClientVersionDAO implements IClientVersionDAO {
    @Autowired
    private SessionFactory mSessionFactory;

    @Override
    public ClientVersion getClientVersion(EOS EOS, int versionCode) throws Exception {
        Query query = mSessionFactory.getCurrentSession().createQuery("from ClientVersion where osEnum=:os and :versionCode between minVersionCode and maxVersionCode");
        query.setParameter("os", EOS);
        query.setParameter("versionCode", versionCode);
        List result = query.list();
        if (result.size() == 0) {
            return null;
        }
        return (ClientVersion) result.get(0);
    }
}
