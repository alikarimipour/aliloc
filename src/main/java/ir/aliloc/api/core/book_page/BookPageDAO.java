/**
 * 12/10/2018
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.book_page;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
class BookPageDAO implements IBookPageDAO {

    @Autowired
    private SessionFactory mSessionFactory;


    @Override
    public List<BookPage> getAllBookPageByBookId(long bookId) throws Exception {
        Query query = mSessionFactory.getCurrentSession().createQuery("from BookPage where book.id=:bookId order by pageNumber");
        query.setParameter("bookId",bookId);
        return query.list();
    }
}
