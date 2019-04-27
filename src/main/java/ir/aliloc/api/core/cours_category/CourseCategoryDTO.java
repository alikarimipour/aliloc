/**
 * 12/22/2018
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.cours_category;

import ir.aliloc.api.core.course.CourseDTO;
import ir.aliloc.api.core.enums.ECourseTypeEnum;
import lombok.Data;
import java.util.List;

@Data
public class CourseCategoryDTO {
    private long id;
    private String title;
    private ECourseTypeEnum type;
    private List<CourseDTO> courseList;
}
