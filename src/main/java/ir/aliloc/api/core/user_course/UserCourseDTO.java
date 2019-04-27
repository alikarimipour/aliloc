/**
 * 12/31/2018
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.user_course;

import ir.aliloc.api.core.course.CourseDTO;
import ir.aliloc.api.core.user.models.UserDTO;
import lombok.Data;

@Data
public class UserCourseDTO {

    private long id;
    private UserDTO user;
    private CourseDTO course;
    private long time;
    private long startTime;
}
