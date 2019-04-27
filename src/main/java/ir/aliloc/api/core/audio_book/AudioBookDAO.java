/**
 * 12/15/2018
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.audio_book;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
class AudioBookDAO implements IAudioBookDAO {

    @Autowired
    private SessionFactory mSessionFactory;

    @Override
    public List<AudioBook> getAllAudioBookList(int offset, int size) throws Exception {
        Query query = mSessionFactory.getCurrentSession().createQuery("from AudioBook");
        query.setFirstResult(offset).setMaxResults(size);
        return query.list();
    }

    @Override
    public AudioBook getAudioBook(Long audioBookId) throws Exception {
        AudioBook audioBook = mSessionFactory.getCurrentSession().get(AudioBook.class,audioBookId);
        return audioBook;
    }


}
