/**
 * Created by alikarimipour157@gmail.com on 9/23/2017.
 */
package ir.aliloc.api.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFacade implements IAuthenticationFaced {
    @Override
    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
