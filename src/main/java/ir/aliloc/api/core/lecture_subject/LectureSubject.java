/**
 * 12/5/2018
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.lecture_subject;

import ir.aliloc.api.core.lecture.Lecture;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "lectureSubject")
public class LectureSubject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "title")
    private String title;

    @Column(name = "isActive")
    private boolean active;

    @OneToMany(cascade = CascadeType.MERGE, orphanRemoval = true, mappedBy = "lectureSubject")
    private List<Lecture> lectures;

}

