/**
 * 12/17/2018
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.question_option;

import java.util.List;

interface IQuestionOptionDAO {

    List<QuestionOption> getQuestionOptionByQuestionIds(List<Long> questionIds) throws Exception;
}
