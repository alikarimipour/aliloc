/**
 * 3/7/2018
 * author: alikarimipour157@gmail.com
 */
package ir.aliloc.api.core.user;


import ir.aliloc.api.config.GlobalConstant;
import ir.aliloc.api.config.MessageConstant;
import ir.aliloc.api.core.multimedia.IMultiMediaService;
import ir.aliloc.api.core.user.models.*;
import ir.aliloc.api.core.user_rate.IUserRateService;
import ir.aliloc.api.core.util.GlobalService;
import ir.aliloc.api.security.IAuthenticationFaced;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.ws.rs.NotAcceptableException;
import java.util.Date;

@Service
@Transactional
class UserService implements IUserService {

    @Autowired
    private IUserDAO<User> mIUserDAO;
    @Autowired
    private ModelMapper mModelMapper;
    @Autowired
    private IAuthenticationFaced mIAuthenticationFaced;
    @Autowired
    private GlobalService mGlobalService;
    @Autowired
    private IMultiMediaService mIMultiMediaService;
    @Autowired
    private IUserRateService mIUserRateService;

    @Override
    public UserDTO getUserDTO() throws Exception {
        long userId = Long.parseLong(mIAuthenticationFaced.getAuthentication().getName());
        return getUserDTOById(userId);
    }

    @Override
    public UserDTO getUserDTOById(long userId) throws Exception {
        User user = mIUserDAO.getUserById(userId);
        if (user == null) {
            return null;
        }
        return mModelMapper.map(mIUserDAO.getUserById(userId), UserDTO.class);
    }

    @Override
    public void updatePasswordById(long id, String password) throws Exception {
        mIUserDAO.updatePasswordById(id, password);
    }

    @Override
    public UserPassDTO addUser(String invitationCode, String mobile) throws Exception {
        User user = new User();
        user.setCreationDate(new Date().getTime());
        user.setMobile(mobile);
        user.setInvitationCode(invitationCode);
        return mModelMapper.map(mIUserDAO.addUser(user), UserPassDTO.class);
    }

    @Override
    public boolean existUser(String mobile) throws Exception {
        return mIUserDAO.exist(mobile);
    }

    @Override
    public UserDTO getUserByMobile(String mobile) throws Exception {

        return mModelMapper.map(mIUserDAO.getUserByMobile(mobile), UserDTO.class);
    }

    @Override
    public UserPassDTO getMainObjectByMobile(String mobile) throws Exception {
        User user = mIUserDAO.getUserByMobile(mobile);
        if (user == null) {
            return null;
        }
        return mModelMapper.map(user, UserPassDTO.class);
    }

    @Override
    public UserRateDTO update(UserRequest userRequest) throws Exception {
        long userId = Long.parseLong(mIAuthenticationFaced.getAuthentication().getName());
        User user = new User();
        if (userRequest.getPicUrl() != null && !userRequest.getPicUrl().isEmpty()) {
            String finalUrl = GlobalConstant.UPLOAD_URL + "/" + GlobalConstant.UPLOAD_PROFILE_BUCKET + "/" + userRequest.getPicUrl();
            user.setAvatar(mIMultiMediaService.addProfilePic(finalUrl));
        }
        user.setId(userId);
        user.setBirthDate(userRequest.getBirthDate());
        user.setLastName(userRequest.getLastName());
        user.setFirstName(userRequest.getFirstName());
        user.setNationalCode(userRequest.getNationalCode());
        user.setGender(userRequest.getGender());
        user.setFatherName(userRequest.getFatherName());
        mIUserDAO.updateUser(user);
        return getUserByRate();
    }

    @Override
    public UserPassDTO getMainObjectById(long userId) throws Exception {
        User user = mIUserDAO.getUserById(userId);
        if (user != null) {
            return mModelMapper.map(user, UserPassDTO.class);
        }
        return new UserPassDTO();

    }

    @Override
    public UserPassDTO getMainObjectUser() throws Exception {
        long userId = Long.parseLong(mIAuthenticationFaced.getAuthentication().getName());
        return getMainObjectById(userId);
    }

    @Override
    public String addPassword(long id) throws Exception {
        UserPassDTO userD = getMainObjectUser();
        if (userD.getPassword() == null || userD.getPassword().isEmpty()) {
            long currentTime = new Date().getTime();
            String password = mGlobalService.generatePassword(currentTime, id);
            mIUserDAO.updatePasswordById(id, password);
            userD.setPassword(password);
        }
        return userD.getPassword();
    }

    @Override
    public UserRateDTO getUserByRate() throws Exception {
        long userId = Long.parseLong(mIAuthenticationFaced.getAuthentication().getName());
        return mIUserDAO.getUserByRate(userId);
    }

    @Override
    public void addInvitationRate(String invitationCode) throws Exception {
        long invitationUserId = mIUserDAO.getUserIdByInvitationCode(invitationCode);
        if (invitationUserId != 0) {
            mIUserRateService.invitationHandler(invitationCode,invitationUserId);
        } else {
            throw new NotAcceptableException(MessageConstant.ERROR_INVITATION_CODE);
        }
    }

    @Override
    public User getLoadObject(long userId) throws Exception {
        return mIUserDAO.getLoadObject(userId);
    }

    @Override
    public UserRateDTO getUserByRate(long userId) throws Exception {
        return mIUserDAO.getUserByRate(userId);
    }

    @Override
    public Long getUserIdByInvitationCode(String invitationCode) throws Exception {
        return mIUserDAO.getUserIdByInvitationCode(invitationCode);
    }

}
