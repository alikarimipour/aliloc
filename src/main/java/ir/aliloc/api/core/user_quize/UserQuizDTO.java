/**
 * 12/31/2018
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.user_quize;

import ir.aliloc.api.core.enums.EQuizResult;
import ir.aliloc.api.core.quize.QuizDTO;
import lombok.Data;

@Data
public class UserQuizDTO {

    private long id;
    private long time;
    private int point;
    private EQuizResult type;
    private QuizDTO quiz;
}
