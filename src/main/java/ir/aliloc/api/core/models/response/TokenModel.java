/**
 * 3/6/2018
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.models.response;


import ir.aliloc.api.core.user.models.UserPassDTO;
import lombok.Data;

@Data
public class TokenModel {

    private String token;
    private UserPassDTO user;
}
