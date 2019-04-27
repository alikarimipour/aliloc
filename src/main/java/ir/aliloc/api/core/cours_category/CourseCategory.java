/**
 * 12/17/2018
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.cours_category;

import ir.aliloc.api.core.course.Course;
import ir.aliloc.api.core.enums.ECourseTypeEnum;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "courseCategory")
public class CourseCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "title")
    private String title;

    @Column(name = "eCourseType")
    private ECourseTypeEnum eCourseTypeEnum;

    @Column(name = "isActive")
    private boolean isActive;

    @OneToMany(cascade = CascadeType.MERGE,orphanRemoval = true,mappedBy = "courseCategory")
    private List<Course> mCourses;

}
