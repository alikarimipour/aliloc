/**
 * Created by alikarimipour157@gmail.com on 9/23/2017.
 */
package ir.aliloc.api.security;

import org.springframework.security.core.Authentication;

public interface IAuthenticationFaced {
    Authentication getAuthentication();
}
