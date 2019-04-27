/**
 * 12/5/2018
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.lecture_subject;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
class LectureSubjectDAO implements ILectureSubjectDAO {

    @Autowired
    private SessionFactory mSessionFactory;

    @Override
    public List<LectureSubject> getSubjectList() throws Exception {
        Query query = mSessionFactory.getCurrentSession().createQuery("from LectureSubject where active=true");
        return query.list();
    }
}
