/**
 * 12/5/2018
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.lecture;

import ir.aliloc.api.core.lecture_subject.LectureSubjectDTO;
import ir.aliloc.api.core.multimedia.MultiMediaDTO;
import lombok.Data;

@Data
public class LectureDTO {

    private long id;
    private String title;
    private LectureSubjectDTO lectureSubject;
    private MultiMediaDTO multiMedia;
    private boolean isDownload;
}
