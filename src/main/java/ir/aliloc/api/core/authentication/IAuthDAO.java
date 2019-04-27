/**
 * 8/7/2018
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.authentication;

import javax.ws.rs.NotFoundException;
import java.util.List;

interface IAuthDAO<T> {

    T addAuth(T auth) throws Exception;

    void updateAuth(T entity) throws Exception;

    void deleteAuth(String entity) throws Exception;

    T getAuth(String entity) throws NotFoundException;

    boolean exist(long userId) throws Exception;

    T getAuthByUserId(long userId) throws Exception;

    long updateVerifyCode(int verifyCode, long userId) throws Exception;

    T updateTokenById(long id, String token) throws Exception;

    List<T> addMembers(List<T> authList) throws Exception;

    T getAuthById(long authId) throws Exception;

    void updateTokenByUserId(long userId, String token) throws Exception;

    T getAuthByMobile(String mobile) throws Exception;
}
