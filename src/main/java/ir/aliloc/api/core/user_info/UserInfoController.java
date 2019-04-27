/**
 * 10/8/2018
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.user_info;


import ir.aliloc.api.config.MessageConstant;
import ir.aliloc.api.exception.CustomizeResponseEntityExceptionHandler;
import ir.aliloc.api.core.models.init.MainModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserInfoController {

    @Autowired
    private UserInfoService mUserInfoService;

    @RequestMapping(value = "/api/user/info/add",method = RequestMethod.POST)
    public ResponseEntity<MainModel<String>> addUserInfoAdd(@RequestBody UserInfoDTO userInfoDTO){
        MainModel<String> mainModel = new MainModel<>();
        try {
            mUserInfoService.addUserInfo(userInfoDTO);
            mainModel.setResult(MessageConstant.MESSAGE_SUCCESS_ADD);
            mainModel.setMessage(MessageConstant.MESSAGE_SUCCESS_ADD);
            mainModel.setStatusCode(HttpStatus.OK.value());
            return new ResponseEntity<>(mainModel, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new CustomizeResponseEntityExceptionHandler().handleAllExceptions(e);
        }
    }
}
