/**
 * 12/31/2018
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.question_option;

import ir.aliloc.api.core.question.QuestionDTO;
import lombok.Data;

@Data
public class QuestionOptionDTO {

    private long id;
    private String text;
    private QuestionDTO question;
}
