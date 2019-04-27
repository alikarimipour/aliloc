/**
 * 12/5/2018
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.lecture;

import ir.aliloc.api.core.lecture_subject.LectureSubject;
import ir.aliloc.api.core.multimedia.MultiMedia;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "lecture")
public class Lecture {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private long id;

    @Column(name = "title")
    private String title;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id")
    private LectureSubject lectureSubject;

    @OneToOne(cascade = CascadeType.MERGE,orphanRemoval = true)
    @JoinColumn(name = "multimedia_id")
    private MultiMedia multiMedia;
}
