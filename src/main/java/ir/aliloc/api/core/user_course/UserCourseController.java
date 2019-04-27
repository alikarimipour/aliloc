/**
 * 12/31/2018
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.user_course;

import ir.aliloc.api.config.MessageConstant;
import ir.aliloc.api.exception.CustomizeResponseEntityExceptionHandler;
import ir.aliloc.api.core.course.CourseDTO;
import ir.aliloc.api.core.models.init.MainModel;
import ir.aliloc.api.core.models.request.CourseIdRequest;
import ir.aliloc.api.core.models.request.CourseTypeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user/course")
public class UserCourseController {
    @Autowired
    private IUserCourseService mIUserCourseService;

    @RequestMapping(value = "/history/all", method = RequestMethod.GET)
    public ResponseEntity<MainModel<List<CourseDTO>>> getAllUserCourse() {
        MainModel<List<CourseDTO>> mainModel = new MainModel<>();
        try {
            mainModel.setStatusCode(HttpStatus.OK.value());
            mainModel.setResult(mIUserCourseService.getAllUserCourse());
            mainModel.setMessage(MessageConstant.MESSAGE_SUCCESS);
            return new ResponseEntity<>(mainModel, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new CustomizeResponseEntityExceptionHandler().handleAllExceptions(e);
        }
    }

    @RequestMapping(value = "/history/type", method = RequestMethod.POST)
    public ResponseEntity<MainModel<List<CourseDTO>>> getAllUserCourseByType(@RequestBody CourseTypeRequest courseTypeRequest) {
        MainModel<List<CourseDTO>> mainModel = new MainModel<>();
        try {
            mainModel.setStatusCode(HttpStatus.OK.value());
            mainModel.setResult(mIUserCourseService.getHistoryCourseByType(courseTypeRequest.getCourseType()));
            mainModel.setMessage(MessageConstant.MESSAGE_SUCCESS);
            return new ResponseEntity<>(mainModel, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new CustomizeResponseEntityExceptionHandler().handleAllExceptions(e);
        }
    }
    @RequestMapping(value = "/start", method = RequestMethod.POST)
    public ResponseEntity<MainModel<UserCourseDTO>> addUserCourse(@RequestBody CourseIdRequest courseIdRequest) {
        MainModel<UserCourseDTO> mainModel = new MainModel<>();
        try {
            mainModel.setStatusCode(HttpStatus.OK.value());
            mainModel.setResult(mIUserCourseService.addUserCourse(courseIdRequest.getCourseId()));
            mainModel.setMessage(MessageConstant.MESSAGE_SUCCESS);
            return new ResponseEntity<>(mainModel, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new CustomizeResponseEntityExceptionHandler().handleAllExceptions(e);
        }
    }

    @RequestMapping(value = "/doing", method = RequestMethod.POST)
    public ResponseEntity<MainModel<UserCourseDTO>> startUserCourse(@RequestBody CourseIdRequest courseIdRequest) {
        MainModel<UserCourseDTO> mainModel = new MainModel<>();
        try {
            mainModel.setStatusCode(HttpStatus.OK.value());
            UserCourseDTO userCourseDTO = mIUserCourseService.updateUserCourse(courseIdRequest.getCourseId());
            mainModel.setResult(userCourseDTO);
            mainModel.setMessage(MessageConstant.MESSAGE_SUCCESS);
            return new ResponseEntity<>(mainModel, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new CustomizeResponseEntityExceptionHandler().handleAllExceptions(e);
        }
    }
}
