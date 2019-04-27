/**
 * 12/24/2018
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.rate_model;

import ir.aliloc.api.core.user_rate.RateType;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
class RateModelDAO implements IRateModelDAO {

    @Autowired
    private SessionFactory mSessionFactory;

    @Override
    public RateModel getRateModelByType(RateType rateType) throws Exception {
        Query query = mSessionFactory.getCurrentSession().createQuery("from RateModel where rateType=:rateType and active=true");
        query.setParameter("rateType",rateType);
        List result = query.list();
        if (result.size()!=0){
            return (RateModel) result.get(0);
        }else{
            return null;
        }
    }
}
