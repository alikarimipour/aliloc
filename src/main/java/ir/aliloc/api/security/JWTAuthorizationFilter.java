/**
 * 3/7/2018
 * author: alikarimipour157@gmail.com
 */
package ir.aliloc.api.security;


import io.jsonwebtoken.Jwts;
import ir.aliloc.api.config.GlobalConstant;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    public JWTAuthorizationFilter(AuthenticationManager authManager) {
        super(authManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        String header = req.getHeader(GlobalConstant.HEADER_STRING);
        if (header == null || !header.startsWith(GlobalConstant.TOKEN_PREFIX)) {
            chain.doFilter(req, res);
            return;
        }
        UsernamePasswordAuthenticationToken authentication = getAuthentication(req);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(req, res);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(GlobalConstant.HEADER_STRING);
        if (token != null) {
            // parse the token.
            try {
                String user = Jwts.parser()
                        .setSigningKey(GlobalConstant.SECRET_KEY.getBytes())
                        .parseClaimsJws(token.replace(GlobalConstant.TOKEN_PREFIX, ""))
                        .getBody()
                        .getSubject();

                if (user != null) {
                    return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }
        return null;
    }
}
