/**
 * 12/23/2018
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.course;

import ir.aliloc.api.core.enums.ECoursePaymentType;
import lombok.Data;

@Data
public class CourseWithoutRelationDTO {

    private long id;
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
}
