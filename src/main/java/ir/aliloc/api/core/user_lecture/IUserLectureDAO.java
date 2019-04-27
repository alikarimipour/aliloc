/**
 * 12/25/2018
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.user_lecture;

import ir.aliloc.api.core.lecture.Lecture;

import java.util.List;

interface IUserLectureDAO {

    void addUserLecture(UserLecture userLecture) throws Exception;

    boolean checkUserLecture(long lectureId,long userId) throws Exception;

    List<Lecture> getUserLectureByUserId(long userId) throws Exception;

    List<Long> getDownloadLectureIdByUserId(long userId) throws Exception;
}
