/**
 * 10/7/2018
 * author: alikarimipour157@gmail.com
 */
package ir.aliloc.api.core.user;

import ir.aliloc.api.config.MessageConstant;
import ir.aliloc.api.core.models.init.MainModel;
import ir.aliloc.api.core.models.request.InvitationCodeRequest;
import ir.aliloc.api.core.user.models.UserRateDTO;
import ir.aliloc.api.core.user.models.UserRequest;
import ir.aliloc.api.exception.CustomizeResponseEntityExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api/user")
@RestController
public class UserController {

    @Autowired
    private UserService mUserService;

    @RequestMapping(value = "/invite/code", method = RequestMethod.POST)
    public ResponseEntity<MainModel<String>> invitationCode(@RequestBody InvitationCodeRequest invitationCodeRequest) {
        MainModel<String> mainModel = new MainModel<>();
        try {
            mUserService.addInvitationRate(invitationCodeRequest.getInvitationCode());
            mainModel.setResult(MessageConstant.MESSAGE_SUCCESS_ADD);
            mainModel.setMessage(MessageConstant.MESSAGE_SUCCESS_ADD);
            mainModel.setStatusCode(HttpStatus.OK.value());
            return new ResponseEntity<>(mainModel, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new CustomizeResponseEntityExceptionHandler().handleAllExceptions(e);
        }
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public ResponseEntity<MainModel<UserRateDTO>> getUser() {
        MainModel<UserRateDTO> mainModel = new MainModel<>();
        try {
            mainModel.setResult(mUserService.getUserByRate());
            mainModel.setStatusCode(HttpStatus.OK.value());
            mainModel.setMessage(MessageConstant.MESSAGE_SUCCESS);
            return new ResponseEntity<>(mainModel, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new CustomizeResponseEntityExceptionHandler().handleAllExceptions(e);
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity<MainModel<UserRateDTO>> addUserInfoAdd(@RequestBody UserRequest userRequest) {
        MainModel<UserRateDTO> mainModel = new MainModel<>();
        try {
            mainModel.setMessage(MessageConstant.MESSAGE_SUCCESS_ADD);
            mainModel.setResult(mUserService.update(userRequest));
            mainModel.setStatusCode(HttpStatus.OK.value());
            return new ResponseEntity<>(mainModel, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new CustomizeResponseEntityExceptionHandler().handleAllExceptions(e);
        }
    }


}
