/**
 * 12/17/2018
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.question;

import ir.aliloc.api.core.models.request.AnswerRequest;
import ir.aliloc.api.core.models.response.QuestionAnswerResponse;

import java.util.List;

public interface IQuestionService {

    List<QuestionAnswerDTO> getListOfQuestionWithAnswer(List<Long> questionIds) throws Exception;

    List<QuestionDTO> getListOfQuestion(long quizId) throws Exception;

    QuestionAnswerResponse checkAnswer(AnswerRequest answerRequest) throws Exception;
}
