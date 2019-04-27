/**
 * 12/5/2018
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.lecture_subject;

import java.util.List;

interface ILectureSubjectDAO {

    List<LectureSubject> getSubjectList() throws Exception;
}
