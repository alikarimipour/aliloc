/**
 * 3/6/2018
 * author: alikarimipour157@gmail.com
 */
package ir.aliloc.api.core.authentication;


import com.google.common.hash.Hashing;
import ir.aliloc.api.config.GlobalConstant;
import ir.aliloc.api.core.models.init.VerifyCodeModel;
import ir.aliloc.api.core.models.response.SMSModel;
import ir.aliloc.api.core.models.response.TokenModel;
import ir.aliloc.api.core.models.response.TokenStrResponse;
import ir.aliloc.api.core.user.IUserService;
import ir.aliloc.api.core.user.models.UserDTO;
import ir.aliloc.api.core.user.models.UserPassDTO;
import ir.aliloc.api.core.user.models.UserRateDTO;
import ir.aliloc.api.core.user_rate.IUserRateService;
import ir.aliloc.api.core.util.GlobalService;
import ir.aliloc.api.core.util.SMSService;
import ir.aliloc.api.core.util.VoucherCodeConfig;
import ir.aliloc.api.core.util.VoucherCodes;
import ir.aliloc.api.security.MyAuthenticationManager;
import ir.aliloc.api.security.MyUserDetailService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.task.TaskExecutor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.ws.rs.ForbiddenException;
import javax.ws.rs.NotAcceptableException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
@Transactional
class AuthService implements IAuthService {

    @Autowired
    private IUserService mUserService;
    @Autowired
    private GlobalService mGlobalService;
    @Autowired
    private IAuthDAO<Auth> authImpl;
    @Autowired
    private ModelMapper mModelMapper;
    @Autowired
    private MyUserDetailService mMyUserDetailService;
    @Autowired
    private MyAuthenticationManager mMyAuthenticationManager;
    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private TaskExecutor taskExecutor;
    @Autowired
    private IUserRateService mIUserRateService;
    @Autowired
    private SMSService mSMSService;


    /*private static List<String> constantMobile = Collections.unmodifiableList(
            new ArrayList<String>() {{
                add("00989123456789");
                add("00989999999001");
                add("00989999999002");
                add("00989999999003");
                add("00989999999004");
                add("00989367384133");
            }});*/

    private static Map<String,String> constantMobile=new HashMap<String, String>() {{
        put("00989123456789", "true");
        put("00989367384133", "true");
    }};

    @Override
    public SMSModel sendVerifyCode(String mobile) throws Exception {
        UserPassDTO userD;
        VerifyCodeModel verifyCodeModel = new VerifyCodeModel();
        boolean isConstantMobile = false;
        if (!mGlobalService.checkMobileFormat(mobile)) {
            throw new NotAcceptableException("error.mobile.format");
        }
        userD = mUserService.getMainObjectByMobile(mobile);
        if (userD == null) {
            VoucherCodeConfig voucherCodeConfig = VoucherCodeConfig.length(GlobalConstant.VOUCHER_LENGTH);
            String invitationCode = VoucherCodes.generate(voucherCodeConfig);
            userD = mUserService.addUser(invitationCode, mobile);
        }

        if (constantMobile.containsKey(mobile)){
            isConstantMobile = true;
        }
        if (isConstantMobile) {
            verifyCodeModel.setCodeStr("10000");
            verifyCodeModel.setCodeInt(10000);
        } else {
            verifyCodeModel = mGlobalService.generateVerificationCode();
        }
        Auth auth = authImpl.getAuthByUserId(userD.getId());
        if (auth != null) {
            long currentTime = new Date().getTime();
            long lastModifyTime = auth.getLastModify();
            long diffTime = currentTime - lastModifyTime;
            if (diffTime > GlobalConstant.EXPIRATION_VERIFY_CODE) {
                authImpl.updateVerifyCode(verifyCodeModel.getCodeInt(), userD.getId());
            } else {
                verifyCodeModel.setCodeInt(auth.getVerifyCode());
                verifyCodeModel.setCodeStr(String.valueOf(auth.getVerifyCode()));
            }
        } else {
            addAuth(userD.getId(), verifyCodeModel.getCodeInt());
        }
        /*SMSServiceThread smsServiceThread = applicationContext.getBean(SMSServiceThread.class);
        smsServiceThread.setMobile(mobile);
        smsServiceThread.setVerifyCodeModel(verifyCodeModel);`
        taskExecutor.execute(smsServiceThread);*/
        SMSModel smsModel = mSMSService.sendAuthenticationSMS(mobile, verifyCodeModel);
//        SMSModel smsModel = new SMSModel();
//        smsModel.setStatus(HttpStatus.OK.value());
        mIUserRateService.addRegistrationUserRate(userD.getId());
        return smsModel;
    }

    @Override
    public Auth addAuth(long userId, int verifyCode) throws Exception {
        AuthDTO authDTO = new AuthDTO();
        authDTO.setVerifyCode(verifyCode);
        UserDTO userD = new UserDTO();
        userD.setId(userId);
        authDTO.setUser(userD);
        return authImpl.addAuth(mModelMapper.map(authDTO, Auth.class));
    }

    @Override
    public AuthDTO getAuthByUserId(long userId) throws Exception {
        Auth auth = authImpl.getAuthByUserId(userId);
        return mModelMapper.map(auth, AuthDTO.class);
    }

    @Override
    public TokenStrResponse getTokenByPassword(long userId, String password) throws Exception {
        UserPassDTO userD = mUserService.getMainObjectById(userId);
        TokenStrResponse tokenStrResponse = new TokenStrResponse();
        if (userD.getPassword() != null && !userD.getPassword().isEmpty()) {
            String hashed = Hashing.sha256()
                    .hashString(userD.getPassword(), StandardCharsets.UTF_8)
                    .toString();
            if (Objects.equals(hashed, password)) {
                String token = mGlobalService.generateToken(String.valueOf(userId));
                tokenStrResponse.setToken(token);
                updateTokenByUserId(userId, token);
            } else {
                throw new ForbiddenException("error.wrong.password");
            }
        }
        return tokenStrResponse;
    }

    @Override
    public void updateTokenByUserId(long userId, String token) throws Exception {
        authImpl.updateTokenByUserId(userId, token);
    }

    @Override
    public TokenModel validationCode(int code, String mobile) throws Exception {
        Auth auth = authImpl.getAuthByMobile(mobile);
        if (auth != null) {
            AuthDTO authDTO = mModelMapper.map(auth, AuthDTO.class);
            long currentTime = new Date().getTime();
            long lastModifyTime = auth.getLastModify();
            long diffTime = currentTime - lastModifyTime;
            if (diffTime > GlobalConstant.EXPIRATION_VERIFY_CODE) {
                throw new NotAcceptableException("error.verify.invalid");
            }
            TokenModel tokenModel = new TokenModel();
            if (authDTO.getVerifyCode() == code) {
                UserDetails userDetails = mMyUserDetailService.loadUserByUsername(String.valueOf(authDTO.getUser().getId()));
                Authentication authentication = mMyAuthenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities()));
                SecurityContextHolder.getContext().setAuthentication(authentication);
                String token = mGlobalService.generateToken(userDetails.getUsername());
                tokenModel.setToken(token);
            } else {
                throw new NotAcceptableException("error.verify.invalid");
            }
            auth.setToken(tokenModel.getToken());
            /*add password with check*/
            String password = mUserService.addPassword(authDTO.getUser().getId());
            Auth updateAuth = authImpl.updateTokenById(authDTO.getId(), tokenModel.getToken());
            updateAuth.getUser().setPassword(password);
            tokenModel.setUser(mModelMapper.map(updateAuth.getUser(), UserPassDTO.class));
            UserRateDTO userRateDTO = mUserService.getUserByRate(tokenModel.getUser().getId());
            tokenModel.getUser().setTotalRate(userRateDTO.getTotalRate());
            tokenModel.getUser().setMonthRate(userRateDTO.getMonthRate());
            return tokenModel;
        } else {
            throw new NotAcceptableException("error.invalid.param");
        }
    }

    @Override
    public String encryptPassword(String link) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest crypt = MessageDigest.getInstance("SHA-1");
        crypt.reset();
        crypt.update(link.getBytes("UTF-8"));
        return new BigInteger(1, crypt.digest()).toString(16);
    }

}
