/**
 * 12/8/2018
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.book_index;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
class BookIndexDAO implements IBookIndexDAO {

    @Autowired
    private SessionFactory mSessionFactory;

    @Override
    public List<BookIndex> getBookIndexByBookId(long bookId) throws Exception {
        Query query = mSessionFactory.getCurrentSession().createQuery("from BookIndex where book.id=:bookId order by page");
        query.setParameter("bookId",bookId);
        return query.list();
    }
}
