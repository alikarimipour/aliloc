/**
 * 11/11/2018
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.client_version;


import ir.aliloc.api.config.MessageConstant;
import ir.aliloc.api.exception.CustomizeResponseEntityExceptionHandler;
import ir.aliloc.api.core.models.init.MainModel;
import ir.aliloc.api.core.models.request.ClientVersionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientVersionController {

    @Autowired
    private IClientVersionService mIClientVersionService;

    @RequestMapping(value = "/clients/version", method = RequestMethod.POST)
    public ResponseEntity<MainModel<ClientVersionDTO>> getClientsVersion(@RequestBody ClientVersionRequest clientVersionRequest) {
        MainModel<ClientVersionDTO> mainModel = new MainModel<>();
        try {
            mainModel.setMessage(MessageConstant.MESSAGE_SUCCESS);
            mainModel.setStatusCode(HttpStatus.OK.value());
            mainModel.setResult(mIClientVersionService.getClientVersion(clientVersionRequest.getOs(), clientVersionRequest.getVersionCode()));
            return new ResponseEntity<>(mainModel, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new CustomizeResponseEntityExceptionHandler().handleAllExceptions(e);
        }
    }

}
