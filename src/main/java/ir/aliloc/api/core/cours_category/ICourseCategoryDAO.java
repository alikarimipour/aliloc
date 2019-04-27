/**
 * 12/17/2018
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.cours_category;

import ir.aliloc.api.core.enums.ECourseTypeEnum;

import java.util.List;

interface ICourseCategoryDAO {

    List<CourseCategory> getAllCourseCategory();
    List<CourseCategory> getAllCourseCategoryByType(ECourseTypeEnum courseTypeEnum);
}
