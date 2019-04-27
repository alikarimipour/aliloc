/**
 * 12/25/2018
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.user_lecture;

import ir.aliloc.api.core.lecture.LectureDTO;

import java.util.List;

public interface IUserLectureService {

    void addUserLecture(long lectureId) throws Exception;

    List<LectureDTO> getDownloadLectureByUserId() throws Exception;

    List<Long> getDownloadLectureIdByUserId(long userId) throws Exception;
}
