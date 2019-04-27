/**
 * 12/10/2018
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.book_page;

import ir.aliloc.api.core.util.CustomMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
class BookPageService implements IBookPageService {

    @Autowired
    private IBookPageDAO mIBookPageDAO;
    @Autowired
    private CustomMapperService mCustomMapperService;

    @Override
    public List<BookPageDTO> getAllBookPageById(long bookId) throws Exception {
        List<BookPage> bookPages = mIBookPageDAO.getAllBookPageByBookId(bookId);
        return mCustomMapperService.bookPageListToBookPageDTOList(bookPages);
    }
}
