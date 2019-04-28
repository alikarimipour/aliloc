/**
 * 3/7/2018
 * author: alikarimipour157@gmail.com
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
