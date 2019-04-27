/**
 * 12/31/2018
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.user_course;

import ir.aliloc.api.core.course.Course;
import ir.aliloc.api.core.enums.ECourseTypeEnum;

import java.util.List;

interface IUserCourseDAO {

    void addUserCourse(UserCourse userCourse) throws Exception;

    void updateUserCourse(UserCourse userCourse) throws Exception;

    List<Course> getHistoryOfUserCourse(long userId, ECourseTypeEnum eCourseTypeEnum) throws Exception;

    List<Course> getAllHistoryCourse(long userId) throws Exception;

    List<UserCourse> getAllHistoryUserCourse(long userId) throws Exception ;

    UserCourse getMainObjectUserCourse(long userId,long courseId) throws Exception;
}
