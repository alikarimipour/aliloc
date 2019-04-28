/**
 * 10/3/2018
 * author: alikarimipour157@gmail.com
 */
package ir.aliloc.api.core.user.models;

import ir.aliloc.api.core.multimedia.MultiMediaDTO;
import ir.aliloc.api.core.user.GenderType;
import lombok.Data;

@Data
public class UserDTO {

    private long id;
    private MultiMediaDTO avatar;
    private String firstName;
    private String lastName;
    private String nationalCode;
    private String fatherName;
    private GenderType gender;
    private long birthDate;
    private long creationDate;
    private String invitationCode;

}
