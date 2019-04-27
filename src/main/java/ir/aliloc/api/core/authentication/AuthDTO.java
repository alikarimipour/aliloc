/**
 * 3/7/2018
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.authentication;

import ir.aliloc.api.core.user.models.UserDTO;
import lombok.Data;

@Data
public class AuthDTO {

    private long id;
    private String token;
    private UserDTO user;
    private long lastModify;
    private int verifyCode;

}
