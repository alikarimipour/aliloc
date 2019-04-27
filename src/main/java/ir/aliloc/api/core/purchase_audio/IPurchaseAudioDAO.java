/**
 * 12/15/2018
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.purchase_audio;

import ir.aliloc.api.core.audio_book.AudioBook;

import java.util.List;

interface IPurchaseAudioDAO {

    List<AudioBook> getHistoryBookAudioByUserId(long userId, int offset, int size) throws Exception;

    List<Long> getAudioBookIdAllBuyUser(long userId) throws Exception;

    AudioBook getPurchaseByBookId(long bookId, long userId) throws Exception;

    boolean checkUserBuyBookAudio(long bookId, long userId) throws Exception;

    void addPurchaseAudio(PurchaseAudio purchaseAudio) throws Exception;
}
