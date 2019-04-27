/**
 * 12/17/2018
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.course;

import ir.aliloc.api.core.enums.ECourseTypeEnum;

import java.util.List;

public interface ICourseService {

    List<CourseDTO> getCourseByType(ECourseTypeEnum type, int offset, int size) throws Exception;

    List<CourseDTO> getCourseByCategoryId(long courseCategoryId, int offset, int size) throws Exception;

    CourseDTO getCourseById(long courseId) throws Exception;

    Course getMainCourseById(long courseId) throws Exception;

    List<CourseDTO> checkActiveUserCourse(List<CourseDTO> courseDTOS) throws Exception;
}
