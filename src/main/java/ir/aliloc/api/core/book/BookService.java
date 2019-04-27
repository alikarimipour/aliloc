/**
 * 12/3/2018
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.book;

import ir.aliloc.api.core.models.request.OffsetSizeRequest;
import ir.aliloc.api.core.purchase_audio.IPurchaseAudioService;
import ir.aliloc.api.core.user_book.IUserBookService;
import ir.aliloc.api.core.user_lecture.IUserLectureService;
import ir.aliloc.api.core.util.CustomMapperService;
import ir.aliloc.api.security.IAuthenticationFaced;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
class BookService implements IBookService {

    @Autowired
    private IBookDAO mIBookDAO;
    @Autowired
    private CustomMapperService mCustomMapperService;
    @Autowired
    private IUserLectureService mIUserLectureService;
    @Autowired
    private IUserBookService mIUserBookService;
    @Autowired
    private IPurchaseAudioService mIPurchaseAudioService;
    @Autowired
    private IAuthenticationFaced mIAuthenticationFaced;

    @Override
    public List<BookDTO> getBookInfoList(OffsetSizeRequest offsetSizeRequest) throws Exception {
        List<Book> bookList = mIBookDAO.getBookInfoList(offsetSizeRequest.getOffset(), offsetSizeRequest.getSize());
        return mCustomMapperService.bookListToBookDTOList(bookList, null, null, null);
    }

    @Override
    public List<BookDTO> getLoginBookList(OffsetSizeRequest offsetSizeRequest) throws Exception {
        long userId = Long.parseLong(mIAuthenticationFaced.getAuthentication().getName());
        List<Book> bookList = mIBookDAO.getBookInfoList(offsetSizeRequest.getOffset(), offsetSizeRequest.getSize());
        List<Long> audioBookBuyId = mIPurchaseAudioService.getAudioBookIdAllBuyUser(userId);
        List<Long> downloadLectureId = mIUserLectureService.getDownloadLectureIdByUserId(userId);
        List<Long> downloadBookId = mIUserBookService.getDownloadedBookIdByUser(userId);
        return mCustomMapperService.bookListToBookDTOList(bookList, downloadBookId, downloadLectureId, audioBookBuyId);
    }

    @Override
    public BookDTO getBookById(long bookId) throws Exception {
        return mCustomMapperService.bookToBookDTO(mIBookDAO.getBookById(bookId),null,null,null);
    }


}
