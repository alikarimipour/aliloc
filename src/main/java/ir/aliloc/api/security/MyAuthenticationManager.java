/**
 * Created by mostafa.mashayekhi9@gmail.com on 9/20/2017.
 */
package ir.aliloc.api.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class MyAuthenticationManager implements AuthenticationManager {
    @Override
    public Authentication authenticate(Authentication auth) throws AuthenticationException {
        if (auth.getName().equals(auth.getCredentials())) {
            throw new BadCredentialsException("Bad Credentials");
        }
        return new UsernamePasswordAuthenticationToken(auth.getName(), auth.getCredentials(), auth.getAuthorities());
    }
}
