/**
 * 1/6/2019
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.models.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnswerInitRequest {
    private long questionId;
    private long answerId;
}