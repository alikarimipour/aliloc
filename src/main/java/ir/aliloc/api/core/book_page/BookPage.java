/**
 * 12/5/2018
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.book_page;

import ir.aliloc.api.core.book.Book;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "bookPage")
public class BookPage {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private long id;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "book_id")
    private Book book;

    @Column(name = "pageNumber")
    private int pageNumber;

    @Column(name = "page", columnDefinition = "TEXT")
    private String page;

    @Column(name = "foot", columnDefinition = "TEXT")
    private String foot;

}
