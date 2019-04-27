/**
 * 12/3/2018
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.book;

import ir.aliloc.api.core.audio_book.AudioBook;
import ir.aliloc.api.core.lecture.Lecture;
import ir.aliloc.api.core.multimedia.MultiMedia;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "title")
    private String title;

    @Column(name = "printOrder")
    private String printOrder;

    @Column(name = "printDate")
    private String printDate;

    @Column(name = "description",columnDefinition="TEXT")
    private String description;

    @Column(name = "price")
    private int price;

    @Column(name = "pageCount")
    private int pageCount;

    @Column(name = "sellLink")
    private String sellLink;

    @Column(name = "startPage")
    private int startPage;

    @OneToOne(cascade = CascadeType.MERGE,orphanRemoval = true)
    @JoinColumn(name = "thumbnail_multimedia_id")
    private MultiMedia thumbnail;

    @ManyToMany(cascade = {CascadeType.MERGE})
    @JoinTable(name = "book_lecture",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "lecture_id"))
    private List<Lecture> lectures;

    @OneToOne(mappedBy = "book",fetch = FetchType.LAZY)
    private AudioBook audioBook;

}
