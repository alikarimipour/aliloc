/**
 * 12/11/2018
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.search;

import ir.aliloc.api.core.audio_book.AudioBookDTO;
import ir.aliloc.api.core.book_index.BookIndexDTO;
import ir.aliloc.api.core.book_page.BookPageDTO;
import ir.aliloc.api.core.multimedia.MimeType;
import ir.aliloc.api.core.multimedia.MultiMediaDTO;
import ir.aliloc.api.core.book.BookDTO;
import ir.aliloc.api.core.enums.ESearchDomain;
import ir.aliloc.api.core.enums.ESearchFilter;
import ir.aliloc.api.core.models.request.SearchBookRequest;
import ir.aliloc.api.core.models.response.SearchBookResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
class SearchService implements ISearchService {

    @Autowired
    private ISearchDAO mISearchDAO;

    @Override
    public List<SearchBookResponse> searchBook(SearchBookRequest searchBookRequest) throws Exception {
        if (searchBookRequest.getSearchDomain() == ESearchDomain.INDEX)
            return searchBookByIndex(searchBookRequest.getText(), searchBookRequest.getSearchFilter(), searchBookRequest.getOffset(), searchBookRequest.getSize());
        if (searchBookRequest.getSearchDomain() == ESearchDomain.TEXT)
            return searchBookByText(searchBookRequest.getText(), searchBookRequest.getSearchFilter(), searchBookRequest.getOffset(), searchBookRequest.getSize());
        if (searchBookRequest.getSearchDomain() == ESearchDomain.TITLE)
            return searchBookByTitle(searchBookRequest.getText(), searchBookRequest.getSearchFilter(), searchBookRequest.getOffset(), searchBookRequest.getSize());
        return null;
    }

    @Override
    public List<SearchBookResponse> searchBookByTitle(String text, ESearchFilter searchFilter, int offset, int size) throws Exception {
        List<Object[]> searchBookResults = mISearchDAO.searchTitleByFilter(text, searchFilter, offset, size);
        List<SearchBookResponse> bookResponses = new ArrayList<>();
        searchBookResults.forEach((item) -> {
            SearchBookResponse searchBookResponse = new SearchBookResponse();
            searchBookResponse.setBook(generateBookInfoDTO(item));
            bookResponses.add(searchBookResponse);
        });
        return bookResponses;
    }

    @Override
    public List<SearchBookResponse> searchBookByIndex(String text, ESearchFilter searchFilter, int offset, int size) throws Exception {
        List<Object[]> searchBookResults = mISearchDAO.searchIndexByFilter(text, searchFilter, offset, size);
        List<SearchBookResponse> bookResponses = new ArrayList<>();
        searchBookResults.forEach((item) -> {
            SearchBookResponse searchBookResponse = new SearchBookResponse();
            BookIndexDTO bookIndexDTO = new BookIndexDTO();
            bookIndexDTO.setId(((BigInteger) item[24]).longValue());
            bookIndexDTO.setTitle((String) item[25]);
            bookIndexDTO.setPage((Integer) item[26]);
            bookIndexDTO.setLevel((Integer) item[27]);
            searchBookResponse.setBookIndex(bookIndexDTO);
            searchBookResponse.setBook(generateBookInfoDTO(item));
            bookResponses.add(searchBookResponse);
        });
        return bookResponses;
    }

    @Override
    public List<SearchBookResponse> searchBookByText(String text, ESearchFilter searchFilter, int offset, int size) throws Exception {
        List<Object[]> searchBookResults = mISearchDAO.searchTextByFilter(text, searchFilter, offset, size);
        List<SearchBookResponse> bookResponses = new ArrayList<>();
        searchBookResults.forEach((item) -> {
            SearchBookResponse searchBookResponse = new SearchBookResponse();
            searchBookResponse.setBook(generateBookInfoDTO(item));
            BookPageDTO bookPageDTO = new BookPageDTO();
            bookPageDTO.setId(((BigInteger) item[24]).longValue());
            bookPageDTO.setPage((String) item[25]);
            bookPageDTO.setPageNumber((Integer) item[26]);
            bookPageDTO.setFoot((String) item[27]);
            searchBookResponse.setBookPage(bookPageDTO);
            bookResponses.add(searchBookResponse);
        });
        return bookResponses;
    }


    private BookDTO generateBookInfoDTO(Object[] item) {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(((BigInteger) item[0]).longValue());
        bookDTO.setDescription((String) (item[1]));
        bookDTO.setPageCount((Integer) item[2]);
        bookDTO.setPrice((Integer) item[3]);
        bookDTO.setPrintDate((String) item[4]);
        bookDTO.setPrintOrder((String) item[5]);
        bookDTO.setTitle((String) item[6]);
        MultiMediaDTO multiMediaDTO = new MultiMediaDTO();
        if (item[7] != null) {
            multiMediaDTO.setId(((BigInteger) item[7]).longValue());
            multiMediaDTO.setDescription((String) item[8]);
            multiMediaDTO.setDuration((Integer) item[9]);
            multiMediaDTO.setMime(MimeType.values()[(Integer) item[10]]);
            multiMediaDTO.setQuality((String) item[11]);
            multiMediaDTO.setSize((Double) item[12]);
            multiMediaDTO.setUrl((String) item[13]);
            bookDTO.setThumbnail(multiMediaDTO);
        }
        if (item[14] != null) {
            AudioBookDTO audioBookDTO = new AudioBookDTO();
            audioBookDTO.setId(((BigInteger) item[14]).longValue());
            audioBookDTO.setPrice((Integer) item[15]);
            audioBookDTO.setTitle((String) item[16]);
            if (item[17] != null) {
                MultiMediaDTO demo = new MultiMediaDTO();
                demo.setId(((BigInteger) item[17]).longValue());
                demo.setDescription((String) item[18]);
                demo.setDuration((Integer) item[19]);
                demo.setMime(MimeType.values()[(int) item[20]]);
                demo.setQuality((String) item[21]);
                demo.setSize((Double) item[22]);
                demo.setUrl((String) item[23]);
                audioBookDTO.setDemo(demo);
            }
            bookDTO.setAudio(audioBookDTO);
        }
        return bookDTO;
    }
}
