/**
 * 11/11/2018
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.certificate;

import ir.aliloc.api.core.course.CourseWithoutRelationDTO;
import ir.aliloc.api.core.user.models.UserDTO;
import lombok.Data;

@Data
public class CertificateDTO {

    private long id;
    private long time;
    private CourseWithoutRelationDTO course;
    private UserDTO user;
    private boolean bought;
    private String url="http://cdn1.motahari.ir:9000/certificates/cert_sample.pdf";

}
