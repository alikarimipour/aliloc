/**
 * 12/31/2018
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.user_quize;

import ir.aliloc.api.core.enums.EQuizResult;

import java.util.List;

public interface IUserQuizService {

    List<UserQuizDTO> getHistoryOfUserQuiz(int offset,int size) throws Exception;

    void addQuizUser(long userId,long quizId,int rightAnswerCount, EQuizResult eQuizResult) throws Exception;

    UserQuiz getCustomUserQuizByUserId(long userId,long quizId) throws Exception;
}
