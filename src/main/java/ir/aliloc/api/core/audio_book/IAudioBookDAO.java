/**
 * 12/15/2018
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.audio_book;

import java.util.List;

interface IAudioBookDAO {

    List<AudioBook> getAllAudioBookList(int offset, int size) throws Exception;

    AudioBook getAudioBook(Long audioBookId) throws Exception;
}
