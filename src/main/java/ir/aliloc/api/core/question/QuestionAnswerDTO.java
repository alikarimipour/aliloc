/**
 * 1/6/2019
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.question;

import ir.aliloc.api.core.question_option.OptionAnswerDTO;
import ir.aliloc.api.core.enums.EQuestionType;
import lombok.Data;

import java.util.List;

@Data
public class QuestionAnswerDTO {
    private long id;
    private String text;
    private EQuestionType type;
    private List<OptionAnswerDTO> options;
}
