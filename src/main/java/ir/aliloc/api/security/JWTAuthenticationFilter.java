/**
 * 3/7/2018
 * author: alikarimipour157@gmail.com
 */
package ir.aliloc.api.security;


import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import ir.aliloc.api.config.GlobalConstant;
import ir.aliloc.api.core.user.models.UserPassDTO;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private MyAuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(MyAuthenticationManager mMyAuthenticationManager) {
        this.authenticationManager = mMyAuthenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) throws AuthenticationException {
        try {
            UserPassDTO userD = new ObjectMapper().readValue(req.getInputStream(), UserPassDTO.class);
            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userD.getMobile(),userD.getMobile(),new ArrayList<>()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain, Authentication auth) throws IOException, ServletException {
        String token = Jwts.builder()
                .setSubject(String.valueOf(auth.getPrincipal()))
                .setExpiration(new Date(System.currentTimeMillis() + GlobalConstant.EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, GlobalConstant.SECRET_KEY.getBytes())
                .compact();
        res.addHeader(GlobalConstant.HEADER_STRING, GlobalConstant.TOKEN_PREFIX + token);
        chain.doFilter(req, res);
    }
}
