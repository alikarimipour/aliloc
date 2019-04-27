/**
 * 12/31/2018
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.user_course;

import ir.aliloc.api.core.course.CourseDTO;
import ir.aliloc.api.core.enums.ECourseTypeEnum;

import java.util.List;

public interface IUserCourseService {

    UserCourseDTO addUserCourse(long courseId) throws Exception;

    List<CourseDTO> getHistoryCourseByType(ECourseTypeEnum eCourseTypeEnum) throws Exception;

    List<CourseDTO> getAllUserCourse() throws Exception;

    List<UserCourseDTO> getAllUserCourseDTO() throws Exception;

    boolean checkUserCourse(long userId, long courseId) throws Exception;

    UserCourse getMainUserCourse(long userId, long courseId) throws Exception;

    UserCourseDTO updateUserCourse(long courseId) throws Exception;

}
