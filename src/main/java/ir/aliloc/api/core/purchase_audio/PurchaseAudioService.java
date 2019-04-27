/**
 * 12/15/2018
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.purchase_audio;

import ir.aliloc.api.config.MessageConstant;
import ir.aliloc.api.security.IAuthenticationFaced;
import ir.aliloc.api.core.audio_book.AudioBook;
import ir.aliloc.api.core.book.BookDTO;
import ir.aliloc.api.core.util.CustomMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.ws.rs.NotFoundException;
import java.util.List;

@Service
@Transactional
class PurchaseAudioService implements IPurchaseAudioService {

    @Autowired
    private IPurchaseAudioDAO mIPurchaseAudioDAO;
    @Autowired
    private IAuthenticationFaced mIAuthenticationFaced;
    @Autowired
    private CustomMapperService mCustomMapperService;

    @Override
    public List<BookDTO> getBookAudioListOfUser(int offset, int size) throws Exception {
        long userId = Long.parseLong(mIAuthenticationFaced.getAuthentication().getName());
        List<AudioBook> audioBookList = mIPurchaseAudioDAO.getHistoryBookAudioByUserId(userId, offset, size);
        return mCustomMapperService.audioBookListToBookDTO(audioBookList,true);
    }

    @Override
    public List<Long> getAudioBookIdAllBuyUser(long userId) throws Exception {
        return mIPurchaseAudioDAO.getAudioBookIdAllBuyUser(userId);
    }

    @Override
    public BookDTO getAudioBookPurchaseByBookId(long bookId) throws Exception {
        long userId = Long.parseLong(mIAuthenticationFaced.getAuthentication().getName());
        AudioBook audioBook = mIPurchaseAudioDAO.getPurchaseByBookId(bookId, userId);
        if (audioBook == null) {
            throw new NotFoundException(MessageConstant.ERROR_PURCHASE_AUDIO_NOT_BUY);
        }
        return mCustomMapperService.audioBookToBookDTO(audioBook,true);
    }

    @Override
    public boolean checkBookBuyByUser(long userId, long bookId) throws Exception {
        return mIPurchaseAudioDAO.checkUserBuyBookAudio(bookId,userId);
    }

    @Override
    public void addAudioPurchase(PurchaseAudio purchaseAudio) throws Exception {
        mIPurchaseAudioDAO.addPurchaseAudio(purchaseAudio);
    }
}
