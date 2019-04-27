/**
 * 12/5/2018
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.lecture_subject;

import java.util.List;

public interface ILectureSubjectService {

    List<LectureSubjectDTO> getSubjectList() throws Exception;

    List<LectureSubjectDTO> getSubjectListWhithDownloaded() throws Exception;
}
