/**
 * 12/31/2018
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.user_course;

import ir.aliloc.api.config.MessageConstant;
import ir.aliloc.api.security.IAuthenticationFaced;
import ir.aliloc.api.core.course.Course;
import ir.aliloc.api.core.course.CourseDTO;
import ir.aliloc.api.core.enums.ECourseTypeEnum;
import ir.aliloc.api.core.user.models.User;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.ws.rs.ForbiddenException;
import java.lang.reflect.Type;
import java.util.Calendar;
import java.util.List;

@Service
@Transactional
class UserCourseService implements IUserCourseService {
    @Autowired
    private IUserCourseDAO mIUserCourseDAO;
    @Autowired
    private IAuthenticationFaced mIAuthenticationFaced;
    @Autowired
    private ModelMapper mModelMapper;

    @Override
    public UserCourseDTO addUserCourse(long courseId) throws Exception {
        long userId = Long.parseLong(mIAuthenticationFaced.getAuthentication().getName());
        if (!this.checkUserCourse(userId, courseId)) {
            User user = new User();
            user.setId(userId);
            Course course = new Course();
            course.setId(courseId);
            UserCourse userCourse = new UserCourse();
            userCourse.setCourse(course);
            userCourse.setTime(Calendar.getInstance().getTimeInMillis());
            userCourse.setUser(user);
            mIUserCourseDAO.addUserCourse(userCourse);
            return mModelMapper.map(userCourse, UserCourseDTO.class);
        } else {
            throw new ForbiddenException(MessageConstant.ERROR_USER_IS_SUBMITED);
        }
    }

    @Override
    public List<CourseDTO> getHistoryCourseByType(ECourseTypeEnum eCourseTypeEnum) throws Exception {
        long userId = Long.parseLong(mIAuthenticationFaced.getAuthentication().getName());
        List<Course> courseList = mIUserCourseDAO.getHistoryOfUserCourse(userId, eCourseTypeEnum);
        Type courseDTOType = new TypeToken<List<CourseDTO>>() {
        }.getType();
        return mModelMapper.map(courseList, courseDTOType);

    }

    @Override
    public List<CourseDTO> getAllUserCourse() throws Exception {
        long userId = Long.parseLong(mIAuthenticationFaced.getAuthentication().getName());
        List<Course> courseList = mIUserCourseDAO.getAllHistoryCourse(userId);
        Type courseDTOType = new TypeToken<List<CourseDTO>>() {
        }.getType();
        return mModelMapper.map(courseList, courseDTOType);
    }

    @Override
    public List<UserCourseDTO> getAllUserCourseDTO() throws Exception {
        if (!mIAuthenticationFaced.getAuthentication().getName().equals("anonymousUser")){

            long userId = Long.parseLong(mIAuthenticationFaced.getAuthentication().getName());
            Type userCourseDTOSType = new TypeToken<List<UserCourseDTO>>() {}.getType();
            List<UserCourseDTO> courseListDTO = mModelMapper.map(mIUserCourseDAO.getAllHistoryUserCourse(userId), userCourseDTOSType)  ;
            return courseListDTO;
        }else {
            return null;
        }
    }

    @Override
    public boolean checkUserCourse(long userId, long courseId) throws Exception {
        UserCourse userCourse = mIUserCourseDAO.getMainObjectUserCourse(userId, courseId);
        return userCourse != null;
    }

    @Override
    public UserCourse getMainUserCourse(long userId, long courseId) throws Exception {
        return mIUserCourseDAO.getMainObjectUserCourse(userId, courseId);
    }

    @Override
    public UserCourseDTO updateUserCourse(long courseId) throws Exception {
        long userId = Long.parseLong(mIAuthenticationFaced.getAuthentication().getName());
        if (this.checkUserCourse(userId, courseId)) {
            UserCourse userCourse= mIUserCourseDAO.getMainObjectUserCourse(userId, courseId);
//            userCourse.setCourse(null);
            userCourse.setStartTime(Calendar.getInstance().getTimeInMillis());
            mIUserCourseDAO.updateUserCourse(userCourse);
            return mModelMapper.map(userCourse,UserCourseDTO.class);
        }else{
            throw new ForbiddenException(MessageConstant.ERROR_USER_IS_NOT_SUBMITED);
        }
    }
}
