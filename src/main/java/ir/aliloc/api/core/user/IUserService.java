/**
 * 10/9/2018
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.user;


import ir.aliloc.api.core.user.models.UserRequest;
import ir.aliloc.api.core.user.models.UserRateDTO;
import ir.aliloc.api.core.user.models.User;
import ir.aliloc.api.core.user.models.UserPassDTO;
import ir.aliloc.api.core.user.models.UserDTO;

public interface IUserService {

    UserDTO getUserDTO() throws Exception;

    UserDTO getUserDTOById(long userId) throws Exception;

    void updatePasswordById(long id, String password) throws Exception;

    UserPassDTO addUser(String invitationCode, String mobile) throws Exception;

    boolean existUser(String mobile) throws Exception;

    UserDTO getUserByMobile(String mobile) throws Exception;

    UserPassDTO getMainObjectByMobile(String mobile) throws Exception;

    UserRateDTO update(UserRequest userRequest) throws Exception;

    UserPassDTO getMainObjectById(long userId) throws Exception;

    UserPassDTO getMainObjectUser() throws Exception;

    String addPassword(long id) throws Exception;

    UserRateDTO getUserByRate() throws Exception;

    void addInvitationRate(String invitationCode) throws Exception;

    User getLoadObject(long userId) throws Exception;

    UserRateDTO getUserByRate(long userId) throws Exception;

    Long getUserIdByInvitationCode(String invitationCode) throws Exception;
}
