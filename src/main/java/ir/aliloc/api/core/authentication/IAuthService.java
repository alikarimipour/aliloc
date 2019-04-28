/**
 * 10/9/2018
 * author: alikarimipour157@gmail.com
 */
package ir.aliloc.api.core.authentication;


import ir.aliloc.api.core.models.response.SMSModel;
import ir.aliloc.api.core.models.response.TokenModel;
import ir.aliloc.api.core.models.response.TokenStrResponse;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public interface IAuthService {

    SMSModel sendVerifyCode(String mobile) throws Exception;

    Auth addAuth(long userId, int verifyCode) throws Exception;

    AuthDTO getAuthByUserId(long userId) throws Exception;

    TokenStrResponse getTokenByPassword(long userId, String password) throws Exception;

    void updateTokenByUserId(long userId, String token) throws Exception;

    TokenModel validationCode(int code, String info) throws Exception;

    String encryptPassword(String link) throws NoSuchAlgorithmException, UnsupportedEncodingException;

}
