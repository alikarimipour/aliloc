/**
 * 12/17/2018
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.course;

import ir.aliloc.api.core.cours_category.CourseCategory;
import ir.aliloc.api.core.multimedia.MultiMedia;
import ir.aliloc.api.core.enums.ECoursePaymentType;
import ir.aliloc.api.core.quize.Quiz;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "courseCategory_id")
    private CourseCategory courseCategory;

    @OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "preCourse_id")
    private Course preCourse;

    @OneToOne(cascade = CascadeType.MERGE,fetch = FetchType.LAZY,orphanRemoval = true)
    @JoinColumn(name = "banner_multimedia_id")
    private MultiMedia multiMedia;

    @OneToOne(cascade = CascadeType.MERGE, orphanRemoval = true)
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "teacher")
    private String teacher;

    @Column(name = "price")
    private int price;

    @Column(name = "eCoursePaymentType")
    private ECoursePaymentType eCoursePaymentType;

    @Column(name = "startTime")
    private long startTime;

    @Column(name = "expireTime")
    private long expireTime;

    @Column(name = "maxFeasibleDays")
    private int maxFeasibleDays;

    @Column(name = "passGrade")
    private float passGrade;

    @Column(name = "minDelayQuiz")
    private int minDelayQuiz;

    @Column(name = "isActive")
    private boolean isActive;

}
