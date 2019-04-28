/**
 * 11/14/2018
 * author: alikarimipour157@gmail.com
 */
package ir.aliloc.api.core.upload;


import ir.aliloc.api.core.models.init.MainModel;
import ir.aliloc.api.core.models.response.UploadTokenResponse;
import ir.aliloc.api.exception.CustomizeResponseEntityExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UploadController {

    @Autowired
    private IUploadService mIUploadService;

    @RequestMapping(value = "api/upload/token", method = RequestMethod.GET)
    public ResponseEntity<MainModel<UploadTokenResponse>> getUploadMinioToken() {
        MainModel<UploadTokenResponse> mainModel = new MainModel<>();
        try {
            mainModel.setResult(mIUploadService.getUploadToken());
            mainModel.setMessage("success");
            mainModel.setStatusCode(HttpStatus.OK.value());
            return new ResponseEntity<>(mainModel, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new CustomizeResponseEntityExceptionHandler().handleAllExceptions(e);
        }
    }
}
