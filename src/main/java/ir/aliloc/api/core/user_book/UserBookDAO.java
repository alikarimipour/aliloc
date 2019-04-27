/**
 * 12/29/2018
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.user_book;

import ir.aliloc.api.core.book.Book;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
class UserBookDAO implements IUserBookDAO {

    @Autowired
    private SessionFactory mSessionFactory;

    @Override
    public List<Book> getBookHistoryByUserId(long userId, int offset, int size) throws Exception {
        Query query = mSessionFactory.getCurrentSession().createQuery("select ub.book from UserBook as ub where ub.user.id=:userId order by ub.time desc ");
        query.setParameter("userId", userId);
        query.setFirstResult(offset).setMaxResults(size);
        return query.list();
    }

    @Override
    public List<Long> getDownloadedBookIdByUser(long userId) throws Exception {
        Query query = mSessionFactory.getCurrentSession().createQuery("select ub.book.id from UserBook as ub where ub.user.id=:userId");
        query.setParameter("userId",userId);
        return query.list();
    }

    @Override
    public void addUserBook(UserBook userBook) throws Exception {
        mSessionFactory.getCurrentSession().save(userBook);
    }

    @Override
    public boolean checkUserBook(long userBookId, long userId) throws Exception {
        Query query = mSessionFactory.getCurrentSession().createQuery("from UserBook where book.id=:bookId and user.id=:userId");
        query.setParameter("userId", userId);
        query.setParameter("bookId", userBookId);
        List result = query.list();
        return result.size() != 0;
    }
}
