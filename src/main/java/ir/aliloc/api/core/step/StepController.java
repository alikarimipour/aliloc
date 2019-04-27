/**
 * 12/18/2018
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.step;

import ir.aliloc.api.config.MessageConstant;
import ir.aliloc.api.core.models.init.MainModel;
import ir.aliloc.api.core.models.request.CourseIdRequest;
import ir.aliloc.api.core.models.request.StepIdRequest;
import ir.aliloc.api.exception.CustomizeResponseEntityExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StepController {

    @Autowired
    private IStepService mIStepService;

    @RequestMapping(value = "/step/list", method = RequestMethod.POST)
    public ResponseEntity<MainModel<List<StepDTO>>> getStepList(@RequestBody CourseIdRequest courseIdRequest) {
        MainModel<List<StepDTO>> mainModel = new MainModel<>();
        try {
            mainModel.setMessage(MessageConstant.MESSAGE_SUCCESS);
            mainModel.setResult(mIStepService.getStepListOfCourse(courseIdRequest.getCourseId()));
            mainModel.setStatusCode(HttpStatus.OK.value());
            return new ResponseEntity<>(mainModel, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new CustomizeResponseEntityExceptionHandler().handleAllExceptions(e);
        }
    }

    @RequestMapping(value = "/step/getById", method = RequestMethod.POST)
    public ResponseEntity<MainModel<StepDTO>> getStepList(@RequestBody StepIdRequest stepIdRequest) {
        MainModel<StepDTO> mainModel = new MainModel<>();
        try {
            mainModel.setMessage(MessageConstant.MESSAGE_SUCCESS);
            mainModel.setResult(mIStepService.getStepById(stepIdRequest.getStepId()));
            mainModel.setStatusCode(HttpStatus.OK.value());
            return new ResponseEntity<>(mainModel, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new CustomizeResponseEntityExceptionHandler().handleAllExceptions(e);
        }
    }

}
