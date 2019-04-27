/**
 * 12/4/2018
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.audio_book;

import ir.aliloc.api.core.multimedia.MultiMedia;
import ir.aliloc.api.core.book.Book;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "audio_book")
public class AudioBook {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "title")
    private String title;

    @OneToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @Column(name = "price")
    private int price;

    @OneToOne(cascade = CascadeType.MERGE, orphanRemoval = true)
    @JoinColumn(name = "demo_id")
    private MultiMedia demo;

    @OneToMany(cascade = CascadeType.MERGE, orphanRemoval = true,fetch = FetchType.LAZY)
    @JoinTable(name = "audioBook_multimedia",
            joinColumns = @JoinColumn(name = "audioBook_id"),
            inverseJoinColumns = @JoinColumn(name = "multimedia_id"))
    private Set<MultiMedia> multimedia;

}
