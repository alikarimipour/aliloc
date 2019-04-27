/**
 * 12/3/2018
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.book;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
class BookDAO implements IBookDAO {

    @Autowired
    private SessionFactory mSessionFactory;

    @Override
    public List<Book> getBookInfoList(int offset, int size) throws Exception {
        Query query = mSessionFactory.getCurrentSession().createQuery("from Book order by title ASC");
        query.setFirstResult(offset).setMaxResults(size);
        return query.list();
    }

    @Override
    public Book getBookById(long bookId) throws Exception {
        Query query = mSessionFactory.getCurrentSession().createQuery("from Book where id=:bookId");
        query.setParameter("bookId",bookId);
        return (Book) query.uniqueResult();
    }
}
