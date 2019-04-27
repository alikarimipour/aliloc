/**
 * 12/18/2018
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.step;

import ir.aliloc.api.core.book_index.BookIndex;
import ir.aliloc.api.core.course.Course;
import ir.aliloc.api.core.multimedia.MultiMedia;
import ir.aliloc.api.core.quize.Quiz;
import ir.aliloc.api.core.book.Book;
import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "step")
public class Step {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Lob
    @Column(name = "text")
    private String text;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "step_multimedia",
            joinColumns = @JoinColumn(name = "step_id"),
            inverseJoinColumns = @JoinColumn(name = "multimedia_id"))
    private List<MultiMedia> multimedia;

    @OneToOne(cascade = CascadeType.MERGE, orphanRemoval = true)
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;

    @OneToOne(cascade = CascadeType.MERGE, orphanRemoval = true)
    @JoinColumn(name = "preStep_id")
    private Step preStep;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToMany(cascade = {CascadeType.MERGE})
    @JoinTable(name = "step_bookIndex",
            joinColumns = @JoinColumn(name = "step_id"),
            inverseJoinColumns = @JoinColumn(name = "bookIndex_id"))
    private Set<BookIndex> bookIndexSet;

}
