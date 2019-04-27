/**
 * 1/6/2019
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.questionBank_question;

import ir.aliloc.api.core.question.Question;
import ir.aliloc.api.core.question_bank.QuestionBank;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "questionBank_question")
public class QuestionBankQuestion {

    @EmbeddedId
    private QuestionBankQuestionId id;

    @ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.LAZY)
    @MapsId("questionBankId")
    private QuestionBank questionBank;

    @ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.LAZY)
    @MapsId("questionId")
    private Question question;




}
