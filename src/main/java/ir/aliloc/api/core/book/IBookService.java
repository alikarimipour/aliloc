/**
 * 12/3/2018
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.book;

import ir.aliloc.api.core.models.request.OffsetSizeRequest;

import java.util.List;

public interface IBookService {

    List<BookDTO> getBookInfoList(OffsetSizeRequest offsetSizeRequest) throws Exception;

    List<BookDTO> getLoginBookList(OffsetSizeRequest offsetSizeRequest) throws Exception;

    BookDTO getBookById(long bookId) throws Exception;
}
