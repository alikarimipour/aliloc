/**
 * 12/5/2018
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.lecture;

import java.util.List;

interface ILectureDAO {

    List<Lecture> getLectureBySubject(long subjectId,int offset, int size) throws Exception;

    Lecture loadObjectById(long lectureId) throws Exception;
}
