/**
 * 12/10/2018
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.book_page;

import java.util.List;

public interface IBookPageService {

    List<BookPageDTO> getAllBookPageById(long bookId) throws Exception;
}
