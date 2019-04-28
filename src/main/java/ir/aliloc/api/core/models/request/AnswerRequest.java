/**
 * 1/6/2019
 * author: alikarimipour157@gmail.com
 */
package ir.aliloc.api.core.models.request;

import lombok.Data;

import java.util.List;

@Data
public class AnswerRequest {

    private List<AnswerInitRequest> answerList;
    private long quizId;
    private long courseId;
    private long stepId;

}



