/**
 * 3/7/2018
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.user;

import ir.aliloc.api.core.user.models.UserRateDTO;

import java.util.List;

interface IUserDAO<T> {

    T addUser(T entity) throws Exception;

    void updateUser(T entity) throws Exception;

    void deleteUser(String mobile) throws Exception;

    T getUserByMobile(String mobile) throws Exception;

    T getUserById(long userId) throws Exception;

    boolean exist(String mobile) throws Exception;

    List<T> popularUsers() throws Exception;

    List<T> getUserDetailsListFromMobile(List<T> userList) throws Exception;

    void updatePasswordById(long userId, String password) throws Exception;

    UserRateDTO getUserByRate(long userId) throws Exception;

    boolean checkInvitationCode(String invitationCode) throws Exception;

    T getLoadObject(long userId) throws Exception;

    Long getUserIdByInvitationCode(String invitationCode) throws Exception;



}
