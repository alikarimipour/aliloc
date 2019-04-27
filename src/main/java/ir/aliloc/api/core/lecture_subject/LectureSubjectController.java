/**
 * 12/5/2018
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.lecture_subject;

import ir.aliloc.api.config.MessageConstant;
import ir.aliloc.api.exception.CustomizeResponseEntityExceptionHandler;
import ir.aliloc.api.core.models.init.MainModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LectureSubjectController {

    @Autowired
    private ILectureSubjectService mILectureSubjectService;

    @RequestMapping(value = "lecture/subjects", method = RequestMethod.GET)
    public ResponseEntity<MainModel<List<LectureSubjectDTO>>> getAllLectureSubject() {
        MainModel<List<LectureSubjectDTO>> mainModel = new MainModel<>();

        try {
            mainModel.setResult(mILectureSubjectService.getSubjectList());
            mainModel.setMessage(MessageConstant.MESSAGE_SUCCESS);
            mainModel.setStatusCode(HttpStatus.OK.value());
            return new ResponseEntity<>(mainModel, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new CustomizeResponseEntityExceptionHandler().handleAllExceptions(e);
        }
    }
    @RequestMapping(value = "api/lecture/subjects", method = RequestMethod.GET)
    public ResponseEntity<MainModel<List<LectureSubjectDTO>>> getAllLectureSubjectWhenUserLogin() {
        MainModel<List<LectureSubjectDTO>> mainModel = new MainModel<>();
        try {
            mainModel.setResult(mILectureSubjectService.getSubjectListWhithDownloaded());
            mainModel.setMessage(MessageConstant.MESSAGE_SUCCESS);
            mainModel.setStatusCode(HttpStatus.OK.value());
            return new ResponseEntity<>(mainModel, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new CustomizeResponseEntityExceptionHandler().handleAllExceptions(e);
        }
    }
}
