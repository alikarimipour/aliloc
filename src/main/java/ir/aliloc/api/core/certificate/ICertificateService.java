/**
 * 12/31/2018
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.certificate;

import ir.aliloc.api.core.models.init.MainModel;
import ir.aliloc.api.core.models.request.CourseIdRequest;
import ir.aliloc.api.core.payment.PaymentDTO;

import java.util.List;

public interface ICertificateService {

    void saveCertificate(Certificate certificate) throws Exception;

    List<CertificateDTO> getCertificateHistory() throws Exception;

    CertificateDTO getCertificate(Long userId, Long courseId);

    MainModel<PaymentDTO> paymentCertificate(CourseIdRequest courseIdRequest) throws Exception;

    Certificate createCertificate(Long userId, Long courseId);

}
