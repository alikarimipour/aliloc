/**
 * 6/27/2018
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import ir.aliloc.api.config.MessageConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

public class MyAuthenticationEntryPoint implements AuthenticationEntryPoint {

    public static final Logger LOGGER = LoggerFactory.getLogger(MyAccessDeniedHandler.class);

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        Map<String, Object> data = new HashMap<>();
        data.put("result", httpServletRequest.getRequestURL().toString());
        data.put("message", MessageConstant.ERROR_ACCESS_DENIED_HANDLER);
        data.put("statusCode", HttpStatus.UNAUTHORIZED.value());
        httpServletResponse.setContentType("application/json");
        ObjectMapper mapper = new ObjectMapper();
        OutputStream out = httpServletResponse.getOutputStream();
//        httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED,"Unauthorized.");
        httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
        mapper.writeValue(out, data);
        out.flush();
    }
}
