/**
 * 12/31/2018
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.certificate;

import java.util.List;

interface ICertificateDAO {

    void addCertificate(Certificate certificate) throws Exception;

    List<Certificate> getCertificateHistory(long userId);

    Certificate getCertificate(long userId, Long courseId);
}
