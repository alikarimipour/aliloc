/**
 * 3/7/2018
 * author: alikarimipour157@gmail.com
 */
package ir.aliloc.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String exception){
        super(exception);
    }
}
