/**
 * 12/16/2018
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.purchase_audio;

import ir.aliloc.api.core.audio_book.AudioBook;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
class PurchaseAudioDAO implements IPurchaseAudioDAO {

    @Autowired
    private SessionFactory mSessionFactory;

    @Override
    public void addPurchaseAudio(PurchaseAudio purchaseAudio) throws Exception {
        mSessionFactory.getCurrentSession().save(purchaseAudio);
    }

    @Override
    public List<AudioBook> getHistoryBookAudioByUserId(long userId, int offset, int size) throws Exception {
        Query query = mSessionFactory.getCurrentSession().createQuery("select pa.audioBook from PurchaseAudio as pa where pa.user.id=:userId order by pa.time desc ");
        query.setParameter("userId", userId);
        query.setFirstResult(offset).setMaxResults(size);
        return query.list();
    }

    @Override
    public List<Long> getAudioBookIdAllBuyUser(long userId) throws Exception {
        Query query = mSessionFactory.getCurrentSession().createQuery("select pa.audioBook.id from PurchaseAudio as pa where pa.user.id=:userId");
        query.setParameter("userId", userId);
        return query.list();
    }

    @Override
    public AudioBook getPurchaseByBookId(long bookId, long userId) throws Exception {
        Query query = mSessionFactory.getCurrentSession().createQuery("select pa.audioBook from PurchaseAudio as pa where pa.audioBook.id=:bookId and pa.user.id=:userId");
        query.setParameter("userId", userId);
        query.setParameter("bookId", bookId);
        List result = query.list();
        if (result.size() != 0) {
            return (AudioBook) result.get(0);
        }
        return null;
    }

    @Override
    public boolean checkUserBuyBookAudio(long bookId, long userId) throws Exception {
        Query query = mSessionFactory.getCurrentSession().createSQLQuery("select count(*) from purchase_audio where user_id=:user_id and audioBook_id=:audioBook_id");
        query.setParameter("user_id", userId);
        query.setParameter("audioBook_id", bookId);
        BigInteger size = (BigInteger) query.uniqueResult();
        return size.compareTo(BigInteger.valueOf(0))==0;
    }
}
