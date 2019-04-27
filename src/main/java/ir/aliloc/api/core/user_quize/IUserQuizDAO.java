/**
 * 12/31/2018
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.user_quize;

import java.util.List;

interface IUserQuizDAO {

    List<UserQuiz> getHistoryOfQuiz(long userId, int offset, int size) throws Exception;

    void addUserQuiz(UserQuiz userQuiz) throws Exception;

    UserQuiz getCustomUserQuizByUserId(long userId,long userQuiz) throws Exception;
}
