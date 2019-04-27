/**
 * 12/8/2018
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.book_index;

import java.util.List;

interface IBookIndexDAO {

    List<BookIndex> getBookIndexByBookId(long bookId) throws Exception;
}
