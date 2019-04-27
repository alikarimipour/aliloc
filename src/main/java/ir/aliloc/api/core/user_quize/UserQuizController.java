/**
 * 12/31/2018
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.user_quize;

import ir.aliloc.api.config.MessageConstant;
import ir.aliloc.api.exception.CustomizeResponseEntityExceptionHandler;
import ir.aliloc.api.core.models.init.MainModel;
import ir.aliloc.api.core.models.request.OffsetSizeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/quiz")
public class UserQuizController {

    @Autowired
    private IUserQuizService mIUserQuizService;

    @RequestMapping(value = "/history",method = RequestMethod.POST)
    public ResponseEntity<MainModel<List<UserQuizDTO>>> getHistory(@RequestBody OffsetSizeRequest offsetSizeRequest){
        MainModel<List<UserQuizDTO>> mainModel = new MainModel<>();
        try {
            mainModel.setStatusCode(HttpStatus.OK.value());
            mainModel.setMessage(MessageConstant.MESSAGE_SUCCESS);
            mainModel.setResult(mIUserQuizService.getHistoryOfUserQuiz(offsetSizeRequest.getOffset(),offsetSizeRequest.getSize()));
            return new ResponseEntity<>(mainModel,HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new CustomizeResponseEntityExceptionHandler().handleAllExceptions(e);
        }
    }
}
