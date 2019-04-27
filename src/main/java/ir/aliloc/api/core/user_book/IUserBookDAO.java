/**
 * 12/29/2018
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.user_book;

import ir.aliloc.api.core.book.Book;

import java.util.List;

interface IUserBookDAO {

    List<Book> getBookHistoryByUserId(long userId, int offset, int size) throws Exception;

    List<Long> getDownloadedBookIdByUser(long userId) throws Exception;

    void addUserBook(UserBook userBook)throws Exception;

    boolean checkUserBook(long userBookId, long userId) throws Exception;
}
