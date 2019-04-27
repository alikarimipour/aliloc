/**
 * 12/10/2018
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.book_page;

import lombok.Data;

@Data
public class BookPageDTO {

    private long id;
    private int pageNumber;
    private String page;
    private String foot;

}
