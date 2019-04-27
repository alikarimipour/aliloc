/**
 * 12/17/2018
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.question;

import ir.aliloc.api.config.MessageConstant;
import ir.aliloc.api.exception.CustomizeResponseEntityExceptionHandler;
import ir.aliloc.api.core.models.init.MainModel;
import ir.aliloc.api.core.models.request.AnswerRequest;
import ir.aliloc.api.core.models.request.QuizIdRequest;
import ir.aliloc.api.core.models.response.QuestionAnswerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/question")
public class QuestionController {

    @Autowired
    private IQuestionService mIQuestionService;

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public ResponseEntity<MainModel<List<QuestionDTO>>> getQuestionList(@RequestBody QuizIdRequest quizIdRequest) {
        MainModel<List<QuestionDTO>> mainModel = new MainModel<>();
        try {
            mainModel.setResult(mIQuestionService.getListOfQuestion(quizIdRequest.getQuizId()));
            mainModel.setMessage(MessageConstant.MESSAGE_SUCCESS);
            mainModel.setStatusCode(HttpStatus.OK.value());
            return new ResponseEntity<>(mainModel, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new CustomizeResponseEntityExceptionHandler().handleAllExceptions(e);
        }
    }

    @RequestMapping(value = "/answer/list", method = RequestMethod.POST)
    public ResponseEntity<MainModel<QuestionAnswerResponse>> answerList(@RequestBody AnswerRequest answerRequest) {
        MainModel<QuestionAnswerResponse> mainModel = new MainModel<>();
        try {
            mainModel.setResult(mIQuestionService.checkAnswer(answerRequest));
            mainModel.setStatusCode(HttpStatus.OK.value());
            mainModel.setMessage(MessageConstant.MESSAGE_SUCCESS);
            return new ResponseEntity<>(mainModel, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new CustomizeResponseEntityExceptionHandler().handleAllExceptions(e);
        }
    }
}
