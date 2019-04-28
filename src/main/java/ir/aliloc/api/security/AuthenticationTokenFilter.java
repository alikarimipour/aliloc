/**
 * 5/3/2018
 * author: alikarimipour157@gmail.com
 */
package ir.aliloc.api.security;


import ir.aliloc.api.config.GlobalConstant;
import ir.aliloc.api.core.authentication.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthenticationTokenFilter extends UsernamePasswordAuthenticationFilter {

    @Autowired
    private IAuthService mAuthService;


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        mAuthService = WebApplicationContextUtils
                .getRequiredWebApplicationContext(this.getServletContext())
                .getBean(IAuthService.class);


        HttpServletResponse resp = (HttpServletResponse) response;
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE, PATCH");
        resp.setHeader("Access-Control-Max-Age", "3600");
//        resp.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, " + GeneralConstant.TOKEN_HEADER);
        resp.setHeader("content-type","content-type=multipart/*");


        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String authToken = httpRequest.getHeader(GlobalConstant.HEADER_STRING);

       /* if (authToken != null) {
            *//*try {
                AuthDTO auth = this.mAuthService.getAuthByUserId(authToken);
                if (auth != null) {

                }else{
                    System.out.println("securityContext");
                }
            } catch (Exception e) {
                throw new NotFoundException(e.getMessage());
//                e.printStackTrace();
            }*//*
        }*/
        chain.doFilter(request, response);
    }
}
