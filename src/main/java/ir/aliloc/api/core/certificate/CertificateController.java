/**
 * 12/31/2018
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.certificate;

import ir.aliloc.api.config.MessageConstant;
import ir.aliloc.api.exception.CustomizeResponseEntityExceptionHandler;
import ir.aliloc.api.core.models.init.MainModel;
import ir.aliloc.api.core.models.request.CourseIdRequest;
import ir.aliloc.api.core.payment.PaymentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CertificateController {
    @Autowired
    private ICertificateService mICertificateService;



    @RequestMapping(value = "/api/clients/certificate/history", method = RequestMethod.POST)
    public ResponseEntity<MainModel<List<CertificateDTO>>> getClientCertificateHistory() {
        MainModel<List<CertificateDTO>> mainModel = new MainModel<>();
        try {
            mainModel.setMessage(MessageConstant.MESSAGE_SUCCESS);
            mainModel.setStatusCode(HttpStatus.OK.value());
            mainModel.setResult(mICertificateService.getCertificateHistory());
            return new ResponseEntity<>(mainModel, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new CustomizeResponseEntityExceptionHandler().handleAllExceptions(e);
        }
    }

    @RequestMapping(value = "/api/clients/buy/certificate", method = RequestMethod.POST)
    public ResponseEntity<MainModel<PaymentDTO>> getClientCertificateHistory(@RequestBody CourseIdRequest courseIdRequest) {
//        MainModel<List<CertificateDTO>> mainModel = new MainModel<>();
        try {

            return new ResponseEntity<>(mICertificateService.paymentCertificate(courseIdRequest), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new CustomizeResponseEntityExceptionHandler().handleAllExceptions(e);
        }
    }

}
