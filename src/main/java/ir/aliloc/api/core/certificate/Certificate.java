/**
 * 12/18/2018
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.certificate;

import ir.aliloc.api.core.course.Course;
import ir.aliloc.api.core.multimedia.MultiMedia;
import ir.aliloc.api.core.user.models.User;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "certificate")
public class Certificate {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "time")
    private long time;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "multimedia_id")
    private MultiMedia multiMedia;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id")
    private User user;


    
}
