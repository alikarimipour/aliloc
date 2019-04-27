/**
 * 12/31/2018
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.question;

import ir.aliloc.api.core.question_option.OptionDTO;
import ir.aliloc.api.core.enums.EQuestionType;
import lombok.Data;

import java.util.List;

@Data
public class QuestionDTO {

    private long id;
    private String text;
    private EQuestionType type;
    private List<OptionDTO> options;
}
