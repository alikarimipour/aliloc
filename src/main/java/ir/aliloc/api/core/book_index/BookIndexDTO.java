/**
 * 12/8/2018
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.book_index;

import lombok.Data;

import java.util.List;

@Data
public class BookIndexDTO {

    private long id;
    private String title;
    private int page;
    private int level;
    private List<BookIndexDTO> subTitles;
}
