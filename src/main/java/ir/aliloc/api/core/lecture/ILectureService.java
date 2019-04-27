/**
 * 12/5/2018
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.lecture;

import java.util.List;

public interface ILectureService {

    List<LectureDTO> getLectureListBySubjectId(long subjectId, int offset, int size) throws Exception;

    Lecture getLoadObjectById(long lectureId) throws Exception;
}
