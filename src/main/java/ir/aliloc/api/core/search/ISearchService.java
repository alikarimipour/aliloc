/**
 * 12/11/2018
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.search;

import ir.aliloc.api.core.enums.ESearchFilter;
import ir.aliloc.api.core.models.request.SearchBookRequest;
import ir.aliloc.api.core.models.response.SearchBookResponse;

import java.util.List;

public interface ISearchService {

    List<SearchBookResponse> searchBook(SearchBookRequest searchBookRequest) throws Exception;

    List<SearchBookResponse> searchBookByTitle(String text, ESearchFilter searchFilter, int offset, int size) throws Exception;

    List<SearchBookResponse> searchBookByIndex(String text, ESearchFilter searchFilter, int offset, int size) throws Exception;

    List<SearchBookResponse> searchBookByText(String text, ESearchFilter searchFilter, int offset, int size) throws Exception;
}
