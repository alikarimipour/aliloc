/**
 * 12/15/2018
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.audio_book;

import ir.aliloc.api.core.purchase_audio.IPurchaseAudioService;
import ir.aliloc.api.security.IAuthenticationFaced;
import ir.aliloc.api.core.book.BookDTO;
import ir.aliloc.api.core.util.CustomMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;

@Service
@Transactional
class AudioBookService implements IAudioBookService {

    @Autowired
    private IAudioBookDAO mIAudioBookDAO;

    @Autowired
    private CustomMapperService mCustomMapperService;
    @Autowired
    private IPurchaseAudioService mIPurchaseAudioService;
    @Autowired
    private IAuthenticationFaced mIAuthenticationFaced;


    @Override
    public List<BookDTO> getAllAudioBook(int offset, int size) throws Exception {
        List<AudioBook> audioBookList = mIAudioBookDAO.getAllAudioBookList(offset, size);
        return mCustomMapperService.audioBookListToBookDTO(audioBookList,false);
    }

    @Override
    public List<BookDTO> getAllAudioBookWhitPurchaseUser(int offset, int size) throws Exception {
        List<AudioBook> audioBookList = mIAudioBookDAO.getAllAudioBookList(offset, size);
        long userId = Long.parseLong(mIAuthenticationFaced.getAuthentication().getName());
        List<Long> purchasedAudioBookIds = mIPurchaseAudioService.getAudioBookIdAllBuyUser(userId);
        HashSet<Long> purchasedAudioBookIdsSet = new HashSet<>(purchasedAudioBookIds);
        List<BookDTO> bookDTOS = mCustomMapperService.audioBookListToAudioBookDTOList(audioBookList, purchasedAudioBookIdsSet);
        return bookDTOS;
    }

    @Override
    public AudioBook getAudioBook(Long audioBookId) throws Exception {
        return mIAudioBookDAO.getAudioBook(audioBookId);
    }

}
