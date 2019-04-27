/**
 * 12/15/2018
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.purchase_audio;

import ir.aliloc.api.core.book.BookDTO;

import java.util.List;

public interface IPurchaseAudioService {

    List<BookDTO> getBookAudioListOfUser(int offset, int size) throws Exception;

    List<Long> getAudioBookIdAllBuyUser(long userId) throws Exception;

    BookDTO getAudioBookPurchaseByBookId(long bookId) throws Exception;

    boolean checkBookBuyByUser(long userId, long bookId) throws Exception;

    void addAudioPurchase(PurchaseAudio purchaseAudio) throws Exception;
}
