/**
 * 12/5/2018
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.lecture_subject;

import ir.aliloc.api.core.lecture.LectureDTO;
import lombok.Data;

import java.util.List;

@Data
public class LectureSubjectDTO {
    private long id;
    private String title;
    private List<LectureDTO> lectures;

}
