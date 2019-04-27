/**
 * 12/17/2018
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.course;

import ir.aliloc.api.core.enums.ECourseTypeEnum;

import java.util.List;

interface ICourseDAO {

    List<Course> getCourseByType(ECourseTypeEnum courseTypeEnum, int offset, int size) throws Exception;

    List<Course> getCourseByCategoryId(long categoryId, int offset, int size) throws Exception;

    Course getCourseById(long courseId) throws Exception;
}
