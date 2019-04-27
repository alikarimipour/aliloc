/**
 * 12/26/2018
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.user.models;

import ir.aliloc.api.core.user.GenderType;
import lombok.Data;

@Data
public class UserBaseDTO {
    private long id;
    private String firstName;
    private String lastName;
    private String nationalCode;
    private String fatherName;
    private GenderType gender;
    private long birthDate;
    private long creationDate;
    private String invitationCode;
}
