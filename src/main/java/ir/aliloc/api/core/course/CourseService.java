/**
 * 12/17/2018
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.course;

import ir.aliloc.api.core.user_course.IUserCourseService;
import ir.aliloc.api.core.user_course.UserCourseDTO;
import ir.aliloc.api.core.enums.ECourseTypeEnum;
import ir.aliloc.api.core.util.CustomMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
@Transactional
class CourseService implements ICourseService {

    @Autowired
    private ICourseDAO mICourseDAO;
    @Autowired
    private CustomMapperService mCustomMapperService;
    @Autowired
    private IUserCourseService mIUserCourseService;

    @Override
    public List<CourseDTO> getCourseByType(ECourseTypeEnum type, int offset, int size) throws Exception {
        List<Course> courseList = mICourseDAO.getCourseByType(type, offset, size);
        return mCustomMapperService.courseListToCourseDTOList(courseList);
    }

    @Override
    public List<CourseDTO> getCourseByCategoryId(long courseCategoryId, int offset, int size) throws Exception {
        List<Course> courseList = mICourseDAO.getCourseByCategoryId(courseCategoryId, offset, size);
        return mCustomMapperService.courseListToCourseDTOList(courseList);
    }

    @Override
    public CourseDTO getCourseById(long courseId) throws Exception {
        Course course = mICourseDAO.getCourseById(courseId);
        if (course != null) {
            return mCustomMapperService.courseToCourseDTO(course, true);
        }
        return null;
    }

    @Override
    public Course getMainCourseById(long courseId) throws Exception {
        return mICourseDAO.getCourseById(courseId);
    }

    @Override
    public List<CourseDTO> checkActiveUserCourse(List<CourseDTO> courseDTOS) throws Exception {
        List<UserCourseDTO> allUserCourse = mIUserCourseService.getAllUserCourseDTO();
        if (allUserCourse != null) {
            Map<Long, UserCourseDTO> userCourses = new HashMap<>();
            for (UserCourseDTO userCourseDTO : allUserCourse) {
                userCourses.put(userCourseDTO.getCourse().getId(), userCourseDTO);
            }
            for (CourseDTO courseDTO : courseDTOS) {
                if (userCourses.containsKey(courseDTO.getId())) {
                    UserCourseDTO userCourseDTO = userCourses.get(courseDTO.getId());
                    courseDTO.setStarting(true);
                    if (userCourseDTO.getStartTime() != 0 && courseDTO.getExpireTime() > Calendar.getInstance().getTimeInMillis() && userCourseDTO.getStartTime() + TimeUnit.DAYS.toMillis(courseDTO.getMaxFeasibleDays()) > Calendar.getInstance().getTimeInMillis()) {
                        courseDTO.setDoing(true);
                    } else {
                        courseDTO.setDoing(false);
                    }
                }
            }
//        return mICourseDAO.getCourseById(courseId);
        }

        return courseDTOS;
    }
}
