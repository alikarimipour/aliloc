/**
 * 12/31/2018
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.quize;

import lombok.Data;

@Data
public class QuizDTO {

    private long id;
    private int passPoint;
    private int questionCount;
    private long time;
    private float coefficient;
}
