/**
 * 1/6/2019
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.questionBank_question;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Setter
@Getter
@Embeddable
public class QuestionBankQuestionId implements Serializable {

    @Column(name = "questionBank_id")
    private long questionBankId;

    @Column(name = "question_id")
    private long questionId;
}
