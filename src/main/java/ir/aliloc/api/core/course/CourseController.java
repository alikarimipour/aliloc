/**
 * 12/17/2018
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.course;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import ir.aliloc.api.config.MessageConstant;
import ir.aliloc.api.core.models.init.MainModel;
import ir.aliloc.api.core.models.request.CourseCategoryIdRequest;
import ir.aliloc.api.core.models.request.CourseIdRequest;
import ir.aliloc.api.core.models.request.CourseTypeRequest;
import ir.aliloc.api.exception.CustomizeResponseEntityExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("course")
public class CourseController {

    @Autowired
    private ICourseService mICourseService;

    @RequestMapping(value = "/list/type", method = RequestMethod.POST)
    public ResponseEntity<MainModel<List<CourseDTO>>> getCourseListByType(@RequestBody CourseTypeRequest courseTypeRequest) {
        MainModel<List<CourseDTO>> mainModel = new MainModel<>();
        try {
            List<CourseDTO> courseDTOS = mICourseService.checkActiveUserCourse(mICourseService.getCourseByType(courseTypeRequest.getCourseType(), courseTypeRequest.getOffset(), courseTypeRequest.getSize()));
            mainModel.setResult(courseDTOS);
            mainModel.setMessage(MessageConstant.MESSAGE_SUCCESS);
            mainModel.setStatusCode(HttpStatus.OK.value());
            return new ResponseEntity<>(mainModel, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new CustomizeResponseEntityExceptionHandler().handleAllExceptions(e);
        }
    }

    @RequestMapping(value = "/list/categoryId", method = RequestMethod.POST)
    public ResponseEntity<MainModel<List<CourseDTO>>> getCourseListByCategoryId(@RequestBody CourseCategoryIdRequest courseCategoryIdRequest) {
        MainModel<List<CourseDTO>> mainModel = new MainModel<>();
        try {
            List<CourseDTO> courseDTOS = mICourseService.checkActiveUserCourse(mICourseService.getCourseByCategoryId(courseCategoryIdRequest.getCourseCategoryId(), courseCategoryIdRequest.getOffset(), courseCategoryIdRequest.getSize()));
            mainModel.setResult(courseDTOS);
            mainModel.setStatusCode(HttpStatus.OK.value());
            mainModel.setMessage(MessageConstant.MESSAGE_SUCCESS);
            return new ResponseEntity<>(mainModel, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new CustomizeResponseEntityExceptionHandler().handleAllExceptions(e);
        }
    }
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization token",
                    required = false, dataType = "string", paramType = "header") })
    @RequestMapping(value = "/info", method = RequestMethod.POST)
    public ResponseEntity<MainModel<CourseDTO>> getCourseDTOById(@RequestBody CourseIdRequest courseIdRequest) {
        MainModel<CourseDTO> mainModel = new MainModel<>();
        try {
            List<CourseDTO> courseDTOS = mICourseService.checkActiveUserCourse(Collections.singletonList(mICourseService.getCourseById(courseIdRequest.getCourseId())));
            mainModel.setMessage(MessageConstant.MESSAGE_SUCCESS);
            mainModel.setResult(courseDTOS.get(0));
            mainModel.setStatusCode(HttpStatus.OK.value());
            return new ResponseEntity<>(mainModel, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new CustomizeResponseEntityExceptionHandler().handleAllExceptions(e);
        }
    }
}
