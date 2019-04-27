/**
 * 12/25/2018
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.user_lecture;

import ir.aliloc.api.core.lecture.Lecture;
import ir.aliloc.api.core.user.models.User;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "user_lecture")
public class UserLecture implements Serializable{

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "lecture_id")
    private Lecture lecture;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "time")
    private long time;

}
