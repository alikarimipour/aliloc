/**
 * 12/31/2018
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.step;

import ir.aliloc.api.core.book_index.BookIndexDTO;
import ir.aliloc.api.core.course.CourseDTO;
import ir.aliloc.api.core.multimedia.MultiMediaDTO;
import ir.aliloc.api.core.quize.QuizDTO;
import ir.aliloc.api.core.book.BookDTO;
import lombok.Data;

import java.util.List;

@Data
public class StepDTO {

    private long id;
    private String title;
    private String description;
    private String text;
    private CourseDTO course;
    private List<MultiMediaDTO> multimedias;
    private QuizDTO quiz;
    private StepDTO preStep;
    private BookDTO book;
    private List<BookIndexDTO> bookIndexList;
    private boolean isAllowed;
    private float quizPoint;
    private boolean isPassed;
}
