/**
 * 12/5/2018
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.lecture;

import ir.aliloc.api.config.MessageConstant;
import ir.aliloc.api.exception.CustomizeResponseEntityExceptionHandler;
import ir.aliloc.api.core.models.init.MainModel;
import ir.aliloc.api.core.models.request.LectureSubjectIdRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/lecture")
public class LectureController {

    @Autowired
    private ILectureService mILectureService;


    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public ResponseEntity<MainModel<List<LectureDTO>>> getAllLecture(@RequestBody LectureSubjectIdRequest lectureSubjectIdRequest) {
        MainModel<List<LectureDTO>> mainModel = new MainModel<>();
        try {
            mainModel.setResult(mILectureService.getLectureListBySubjectId(lectureSubjectIdRequest.getSubjectId(), lectureSubjectIdRequest.getOffset(), lectureSubjectIdRequest.getSize()));
            mainModel.setStatusCode(HttpStatus.OK.value());
            mainModel.setMessage(MessageConstant.MESSAGE_SUCCESS);
            return new ResponseEntity<>(mainModel, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new CustomizeResponseEntityExceptionHandler().handleAllExceptions(e);
        }
    }

}
