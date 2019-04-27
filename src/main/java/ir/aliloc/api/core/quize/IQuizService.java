/**
 * 12/17/2018
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.quize;


public interface IQuizService {

    QuizDTO getQuizById(long quizId) throws Exception;

    Quiz getQuizMainObject(long quizId) throws Exception;
}
