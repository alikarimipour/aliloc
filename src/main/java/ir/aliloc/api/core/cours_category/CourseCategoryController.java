/**
 * 12/17/2018
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.cours_category;

import ir.aliloc.api.config.MessageConstant;
import ir.aliloc.api.exception.CustomizeResponseEntityExceptionHandler;
import ir.aliloc.api.core.models.init.MainModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("course/category")
public class CourseCategoryController {

    @Autowired
    private ICourseCategoryService mICourseCategoryService;


    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseEntity<MainModel<List<CourseCategoryDTO>>> getAllActive() {
        MainModel<List<CourseCategoryDTO>> mainModel = new MainModel<>();
        try {
            mainModel.setResult(mICourseCategoryService.getListOfCourseCategory());
            mainModel.setMessage(MessageConstant.MESSAGE_SUCCESS);
            mainModel.setStatusCode(HttpStatus.OK.value());
            return new ResponseEntity<>(mainModel, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new CustomizeResponseEntityExceptionHandler().handleAllExceptions(e);
        }
    }

    @RequestMapping(value = "/listCategoryByType", method = RequestMethod.POST)
    public ResponseEntity<MainModel<List<CourseCategoryDTO>>> getAllActiveCategoryByType(@RequestBody CourseCategoryDTO courseCategoryDTO) {

        MainModel<List<CourseCategoryDTO>> mainModel = new MainModel<>();
        try {
            mainModel.setResult(mICourseCategoryService.getListOfCourseCategoryByType(courseCategoryDTO.getType()));
            mainModel.setMessage(MessageConstant.MESSAGE_SUCCESS);
            mainModel.setStatusCode(HttpStatus.OK.value());
            return new ResponseEntity<>(mainModel, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new CustomizeResponseEntityExceptionHandler().handleAllExceptions(e);
        }
    }
}
