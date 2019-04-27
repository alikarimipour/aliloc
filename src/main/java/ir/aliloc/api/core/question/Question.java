/**
 * 12/17/2018
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.question;

import ir.aliloc.api.core.book_index.BookIndex;
import ir.aliloc.api.core.question_option.QuestionOption;
import ir.aliloc.api.core.book.Book;
import ir.aliloc.api.core.enums.EQuestionType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Setter
@Getter
@Table(name = "question")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "text")
    private String text;

    @Column(name = "eType")
    private EQuestionType eType;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "bookIndex_id")
    private BookIndex bookIndex;

    @Column(name = "active")
    private boolean active;

    @OneToMany(cascade = CascadeType.MERGE,orphanRemoval = true,mappedBy = "question")
    @OrderBy("text ASC")
    private Set<QuestionOption> options;
}


