/**
 * 12/5/2018
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.lecture_subject;

import ir.aliloc.api.security.IAuthenticationFaced;
import ir.aliloc.api.core.user_lecture.IUserLectureService;
import ir.aliloc.api.core.util.CustomMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;

@Service
@Transactional
class LectureSubjectService implements ILectureSubjectService {

    @Autowired
    private ILectureSubjectDAO mILectureSubjectDAO;
    @Autowired
    private CustomMapperService mCustomMapperService;
    @Autowired
    private IAuthenticationFaced mIAuthenticationFaced;
    @Autowired
    private IUserLectureService mIUserLectureService;


    @Override
    public List<LectureSubjectDTO> getSubjectList() throws Exception {
        return mCustomMapperService.lectureSubjectListToLectureSubjectDTOListWhitLecture(mILectureSubjectDAO.getSubjectList());
    }

    @Override
    public List<LectureSubjectDTO> getSubjectListWhithDownloaded() throws Exception {
        long userId = Long.parseLong(mIAuthenticationFaced.getAuthentication().getName());
        List<Long> downloadLectureId = mIUserLectureService.getDownloadLectureIdByUserId(userId);
        HashSet<Long> lectureDownloadedIds=new HashSet<Long>(downloadLectureId);
        return mCustomMapperService.lectureSubjectListToLectureSubjectDTOListWhitLecture(mILectureSubjectDAO.getSubjectList(),lectureDownloadedIds);
    }
}
