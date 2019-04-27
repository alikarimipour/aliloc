/**
 * 12/5/2018
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.lecture;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
class LectureDAO implements ILectureDAO {
    @Autowired
    private SessionFactory mSessionFactory;


    @Override
    public List<Lecture> getLectureBySubject(long subjectId, int offset, int size) throws Exception {
        Query query = mSessionFactory.getCurrentSession().createQuery("from Lecture where lectureSubject.id=:subjectId");
        query.setParameter("subjectId", subjectId).setFirstResult(offset).setMaxResults(size);
        return query.list();
    }

    @Override
    public Lecture loadObjectById(long lectureId) throws Exception {
        return mSessionFactory.getCurrentSession().load(Lecture.class, lectureId);
    }
}
