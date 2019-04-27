/**
 * 12/29/2018
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.user_book;

import ir.aliloc.api.core.book.BookDTO;

import java.util.List;

public interface IUserBookService {

    List<BookDTO> getBookListOfUser(int offset, int size) throws Exception;

    List<Long> getDownloadedBookIdByUser(long userId) throws Exception;

    void addBookById(long bookId) throws Exception;
}
