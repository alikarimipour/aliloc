/**
 * 12/15/2018
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.audio_book;

import ir.aliloc.api.core.book.BookDTO;

import java.util.List;

public interface IAudioBookService {

    List<BookDTO> getAllAudioBook(int offset, int size) throws Exception;

    List<BookDTO> getAllAudioBookWhitPurchaseUser(int offset, int size) throws Exception;

    AudioBook getAudioBook(Long audioBookId) throws Exception;
}
