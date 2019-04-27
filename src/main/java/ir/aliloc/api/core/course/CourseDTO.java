/**
 * 12/23/2018
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.course;

import ir.aliloc.api.core.multimedia.MultiMediaDTO;
import ir.aliloc.api.core.enums.ECoursePaymentType;
import ir.aliloc.api.core.quize.QuizDTO;
import lombok.Data;

@Data
public class CourseDTO {

    private long id;
    private CourseDTO preCourse;
    private MultiMediaDTO multimedia;
    private String title;
    private String description;
    private String teacher;
    private int price;
    private ECoursePaymentType paymentType;
    private long startTime;
    private long expireTime;
    private int maxFeasibleDays;
    private float passGrade;
    private int minDelayQuiz;
    private boolean isExpired;
    private boolean isDoing;
    private boolean isStarting;
    private QuizDTO quizDTO;
}
