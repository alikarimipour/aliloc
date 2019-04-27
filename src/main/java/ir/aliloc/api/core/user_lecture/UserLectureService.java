/**
 * 12/25/2018
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.user_lecture;

import ir.aliloc.api.security.IAuthenticationFaced;
import ir.aliloc.api.core.lecture.ILectureService;
import ir.aliloc.api.core.lecture.Lecture;
import ir.aliloc.api.core.lecture.LectureDTO;
import ir.aliloc.api.core.user.IUserService;
import ir.aliloc.api.core.user.models.User;
import ir.aliloc.api.core.util.CustomMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.List;

@Service
@Transactional
class UserLectureService implements IUserLectureService {

    @Autowired
    private IUserLectureDAO mIUserLectureDAO;
    @Autowired
    private IAuthenticationFaced mIAuthenticationFaced;
    @Autowired
    private IUserService mIUserService;
    @Autowired
    private ILectureService mILectureService;
    @Autowired
    private CustomMapperService mCustomMapperService;

    @Override
    public void addUserLecture(long lectureId) throws Exception {
        long userId = Long.parseLong(mIAuthenticationFaced.getAuthentication().getName());
        if (!mIUserLectureDAO.checkUserLecture(lectureId, userId)) {
            User user = mIUserService.getLoadObject(userId);
            Lecture lecture = mILectureService.getLoadObjectById(lectureId);
            UserLecture userLecture = new UserLecture();
            userLecture.setTime(Calendar.getInstance().getTimeInMillis());
            userLecture.setUser(user);
            userLecture.setLecture(lecture);
            mIUserLectureDAO.addUserLecture(userLecture);
        }
    }

    @Override
    public List<LectureDTO> getDownloadLectureByUserId() throws Exception {
        long userId = Long.parseLong(mIAuthenticationFaced.getAuthentication().getName());
        List<Lecture> lectureList = mIUserLectureDAO.getUserLectureByUserId(userId);
        return mCustomMapperService.lectureListToLectureDTOList(lectureList,null);
    }

    @Override
    public List<Long> getDownloadLectureIdByUserId(long userId) throws Exception {
        return mIUserLectureDAO.getDownloadLectureIdByUserId(userId);
    }
}
