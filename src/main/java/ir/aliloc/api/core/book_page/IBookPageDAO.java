/**
 * 12/10/2018
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.book_page;

import java.util.List;

interface IBookPageDAO {

    List<BookPage> getAllBookPageByBookId(long bookId) throws Exception;
}
