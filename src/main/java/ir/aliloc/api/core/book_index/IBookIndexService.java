/**
 * 12/8/2018
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.book_index;

import java.util.List;

public interface IBookIndexService {

    List<BookIndexDTO> getBookIndexByBookId(long bookId) throws Exception;
}
