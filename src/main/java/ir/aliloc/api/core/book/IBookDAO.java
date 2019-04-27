/**
 * 12/3/2018
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.book;

import java.util.List;

interface IBookDAO {

    List<Book> getBookInfoList(int offset, int size) throws Exception;

    Book getBookById(long bookId) throws Exception;
}
