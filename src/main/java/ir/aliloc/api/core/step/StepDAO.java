/**
 * 12/18/2018
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.step;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
class StepDAO implements IStepDAO {

    @Autowired
    private SessionFactory mSessionFactory;

    @Override
    public List<Step> getStepListByCourseId(long courseId) throws Exception {
        Query query = mSessionFactory.getCurrentSession().createQuery("from Step where course.id=:courseId");
        query.setParameter("courseId", courseId);
        return query.list();
    }

    @Override
    public Step getStepById(long stepId) throws Exception {
        Query query = mSessionFactory.getCurrentSession().createQuery("from Step where id=:stepId");
        query.setParameter("stepId", stepId);
        return (Step) query.uniqueResult();
    }
}
