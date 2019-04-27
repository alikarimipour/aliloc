/**
 * 1/6/2019
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.models.response;

import ir.aliloc.api.core.enums.EQuizResult;
import ir.aliloc.api.core.models.request.AnswerInitRequest;
import lombok.Data;

import java.util.List;

@Data
public class QuestionAnswerResponse {

    private List<AnswerInitRequest> wrongPointQuestions;
    private int rightPoint;
    private int wrongPoint;
    private EQuizResult quizResult;

}
