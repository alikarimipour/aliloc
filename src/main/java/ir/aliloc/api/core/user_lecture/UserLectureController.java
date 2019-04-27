/**
 * 12/25/2018
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.user_lecture;

import ir.aliloc.api.config.MessageConstant;
import ir.aliloc.api.exception.CustomizeResponseEntityExceptionHandler;
import ir.aliloc.api.core.lecture.LectureDTO;
import ir.aliloc.api.core.models.init.MainModel;
import ir.aliloc.api.core.models.request.LectureIdRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/lecture")
public class UserLectureController {

    @Autowired
    private IUserLectureService mIUserLectureService;

    @RequestMapping(value = "/download", method = RequestMethod.POST)
    public ResponseEntity<MainModel<String>> addUserLecture(@RequestBody LectureIdRequest lectureIdRequest) {
        MainModel<String> mainModel = new MainModel<>();
        try {
            mIUserLectureService.addUserLecture(lectureIdRequest.getLectureId());
            mainModel.setResult(MessageConstant.MESSAGE_SUCCESS_ADD);
            mainModel.setStatusCode(HttpStatus.OK.value());
            mainModel.setMessage(MessageConstant.MESSAGE_SUCCESS_ADD);
            return new ResponseEntity<>(mainModel, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new CustomizeResponseEntityExceptionHandler().handleAllExceptions(e);
        }
    }

    @RequestMapping(value = "/download/history", method = RequestMethod.GET)
    public ResponseEntity<MainModel<List<LectureDTO>>> getDownloadHistory() {
        MainModel<List<LectureDTO>> mainModel = new MainModel<>();

        try {
            mainModel.setMessage(MessageConstant.MESSAGE_SUCCESS);
            mainModel.setResult(mIUserLectureService.getDownloadLectureByUserId());
            mainModel.setStatusCode(HttpStatus.OK.value());
            return new ResponseEntity<>(mainModel, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new CustomizeResponseEntityExceptionHandler().handleAllExceptions(e);
        }
    }
}
