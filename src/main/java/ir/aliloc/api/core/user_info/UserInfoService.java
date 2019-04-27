/**
 * 10/8/2018
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.user_info;

import ir.aliloc.api.security.IAuthenticationFaced;
import ir.aliloc.api.core.user.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;

@Service
@Transactional
class UserInfoService implements IUserInfoService{

    @Autowired
    private IUserInfoDAO mIUserInfoDAO;
    @Autowired
    private IAuthenticationFaced mIAuthenticationFaced;

    public void addUserInfo(UserInfoDTO userInfoDTO) throws Exception{
        long userId = Long.parseLong(mIAuthenticationFaced.getAuthentication().getName());
        User user = new User();
        user.setId(userId);
        UserInfo userInfo = new UserInfo();
        userInfo.setUser(user);
        userInfo.setFbToken(userInfoDTO.getFbToken());
        userInfo.setLastAppVersionFbTokenRefresh(userInfoDTO.getLastAppVersionFbTokenRefresh());
        userInfo.setLastMetadataVersionFbTokenRefresh(userInfoDTO.getLastMetadataVersionFbTokenRefresh());
        userInfo.setUniqueIdentifier(userInfoDTO.getUniqueIdentifier());
        userInfo.setLastFbTokenUpdate(Calendar.getInstance().getTimeInMillis());
        mIUserInfoDAO.addUserInfo(userInfo);
    }
}
