/**
 * 12/5/2018
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.lecture;

import ir.aliloc.api.core.util.CustomMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
class LectureService implements ILectureService {

    @Autowired
    private ILectureDAO mILectureDAO;
    @Autowired
    private CustomMapperService mCustomMapperService;

    @Override
    public List<LectureDTO> getLectureListBySubjectId(long subjectId, int offset, int size) throws Exception {
        return mCustomMapperService.lectureListToLectureDTOList(mILectureDAO.getLectureBySubject(subjectId, offset, size),null);
    }

    @Override
    public Lecture getLoadObjectById(long lectureId) throws Exception {
        return mILectureDAO.loadObjectById(lectureId);
    }
}
