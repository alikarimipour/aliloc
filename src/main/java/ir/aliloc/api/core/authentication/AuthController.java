/**
 * 3/6/2018
 * author: alikarimipour157@gmail.com
 */
package ir.aliloc.api.core.authentication;


import ir.aliloc.api.config.MessageConstant;
import ir.aliloc.api.core.models.init.MainModel;
import ir.aliloc.api.core.models.request.CodeModel;
import ir.aliloc.api.core.models.request.MobileModel;
import ir.aliloc.api.core.models.request.PasswordRequest;
import ir.aliloc.api.core.models.response.SMSModel;
import ir.aliloc.api.core.models.response.TokenModel;
import ir.aliloc.api.core.models.response.TokenStrResponse;
import ir.aliloc.api.exception.CustomizeResponseEntityExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private AuthService mAuthService;

    @RequestMapping(value = "/login/mobile", method = RequestMethod.POST)
    public ResponseEntity<MainModel<SMSModel>> requestVerificationCode(@RequestBody MobileModel mobileModel) {
        MainModel<SMSModel> mainModel = new MainModel<>();
        try {
            SMSModel smsModel = mAuthService.sendVerifyCode(mobileModel.getMobile());
            mainModel.setMessage(MessageConstant.MESSAGE_SUCCESS);
            mainModel.setResult(smsModel);
            mainModel.setStatusCode(HttpStatus.OK.value());
            return new ResponseEntity<>(mainModel, HttpStatus.OK);
        } catch (Exception e) {
            return new CustomizeResponseEntityExceptionHandler().handleAllExceptions(e);
        }
    }

    @RequestMapping(value = "/login/code", method = RequestMethod.POST)
    public ResponseEntity<MainModel<TokenModel>> verificationCode(@RequestBody CodeModel codeModel) {
        MainModel<TokenModel> mainModel = new MainModel<>();
        try {
            TokenModel tokenModel = mAuthService.validationCode(codeModel.getCode(), codeModel.getMobile());
            mainModel.setResult(tokenModel);
            mainModel.setStatusCode(HttpStatus.OK.value());
            mainModel.setMessage(MessageConstant.MESSAGE_SUCCESS);
            return new ResponseEntity<>(mainModel, HttpStatus.OK);
        } catch (Exception e) {
            return new CustomizeResponseEntityExceptionHandler().handleAllExceptions(e);
        }
    }

    @RequestMapping(value = "/token/get", method = RequestMethod.POST)
    public ResponseEntity<MainModel<TokenStrResponse>> getToken(@RequestBody PasswordRequest passwordRequest) {
        MainModel<TokenStrResponse> mainModel = new MainModel<>();
        try {
            mainModel.setStatusCode(HttpStatus.OK.value());
            mainModel.setMessage(MessageConstant.MESSAGE_SUCCESS);
            mainModel.setResult(mAuthService.getTokenByPassword(passwordRequest.getUserId(), passwordRequest.getPassword()));
            return new ResponseEntity<>(mainModel, HttpStatus.OK);
        } catch (Exception e) {
            return new CustomizeResponseEntityExceptionHandler().handleAllExceptions(e);
        }
    }
}
