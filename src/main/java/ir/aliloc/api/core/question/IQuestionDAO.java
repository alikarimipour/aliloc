/**
 * 12/17/2018
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.question;

import ir.aliloc.api.core.question_option.QuestionOption;

import java.util.List;

interface IQuestionDAO {

    List<Long> getQuestionIds(long bookId, long bookIndexId) throws Exception;

    List<Question>  getQuestionListById(List<Long> questionIds) throws Exception;

    List<QuestionOption> getOptionListById(List<Long> optionList) throws Exception;

}
