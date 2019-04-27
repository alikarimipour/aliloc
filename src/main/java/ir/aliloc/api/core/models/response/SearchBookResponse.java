/**
 * 12/11/2018
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.models.response;

import ir.aliloc.api.core.book_index.BookIndexDTO;
import ir.aliloc.api.core.book_page.BookPageDTO;
import ir.aliloc.api.core.book.BookDTO;
import lombok.Data;

@Data
public class SearchBookResponse {

    private BookDTO book;
    private BookIndexDTO bookIndex;
    private BookPageDTO bookPage;

}
