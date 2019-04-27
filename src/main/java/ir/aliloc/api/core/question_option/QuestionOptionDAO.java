/**
 * 12/17/2018
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.question_option;


import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
class QuestionOptionDAO implements IQuestionOptionDAO {

    @Autowired
    private SessionFactory mSessionFactory;

    @Override
    public List<QuestionOption> getQuestionOptionByQuestionIds(List<Long> questionIds) throws Exception {
        Query query = mSessionFactory.getCurrentSession().createQuery("from QuestionOption where question.id in (:ids) and question.active=true");
        query.setParameterList("ids",questionIds);
        return query.list();
    }

}
