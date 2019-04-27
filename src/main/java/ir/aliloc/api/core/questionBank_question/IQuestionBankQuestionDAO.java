/**
 * 1/6/2019
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.questionBank_question;

import java.util.List;

interface IQuestionBankQuestionDAO {

    List<Long> getQuestionIds(long questionBankId) throws Exception;
}
