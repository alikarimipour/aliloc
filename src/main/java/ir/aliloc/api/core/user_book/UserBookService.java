/**
 * 12/29/2018
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.user_book;

import ir.aliloc.api.core.purchase_audio.IPurchaseAudioService;
import ir.aliloc.api.core.user_lecture.IUserLectureService;
import ir.aliloc.api.security.IAuthenticationFaced;
import ir.aliloc.api.core.book.Book;
import ir.aliloc.api.core.book.BookDTO;
import ir.aliloc.api.core.user.models.User;
import ir.aliloc.api.core.util.CustomMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.List;

@Service
@Transactional
class UserBookService implements IUserBookService {

    @Autowired
    private IUserBookDAO mIUserBookDAO;
    @Autowired
    private CustomMapperService mCustomMapperService;
    @Autowired
    private IAuthenticationFaced mIAuthenticationFaced;
    @Autowired
    private IUserLectureService mIUserLectureService;
    @Autowired
    private IUserBookService mIUserBookService;
    @Autowired
    private IPurchaseAudioService mIPurchaseAudioService;

    @Override
    public List<BookDTO> getBookListOfUser(int offset, int size) throws Exception {
        long userId = Long.parseLong(mIAuthenticationFaced.getAuthentication().getName());
        List<Book> bookList = mIUserBookDAO.getBookHistoryByUserId(userId, offset, size);
        List<Long> audioBookBuyId = mIPurchaseAudioService.getAudioBookIdAllBuyUser(userId);
        List<Long> downloadLectureId = mIUserLectureService.getDownloadLectureIdByUserId(userId);
        List<Long> downloadBookId = mIUserBookService.getDownloadedBookIdByUser(userId);
        return mCustomMapperService.bookListToBookDTOList(bookList, downloadBookId, downloadLectureId, audioBookBuyId);
    }

    @Override
    public List<Long> getDownloadedBookIdByUser(long userId) throws Exception {
        return mIUserBookDAO.getDownloadedBookIdByUser(userId);
    }

    @Override
    public void addBookById(long bookId) throws Exception {
        long userId = Long.parseLong(mIAuthenticationFaced.getAuthentication().getName());
        if (!mIUserBookDAO.checkUserBook(bookId, userId)) {
            User user = new User();
            user.setId(userId);
            Book book = new Book();
            book.setId(bookId);
            UserBook userBook = new UserBook();
            userBook.setBook(book);
            userBook.setUser(user);
            userBook.setTime(Calendar.getInstance().getTimeInMillis());
            mIUserBookDAO.addUserBook(userBook);
        }
    }
}
