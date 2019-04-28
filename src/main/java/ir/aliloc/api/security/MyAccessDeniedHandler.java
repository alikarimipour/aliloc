/**
 * 6/27/2018
 * author: alikarimipour157@gmail.com
 */
package ir.aliloc.api.security;


import com.fasterxml.jackson.databind.ObjectMapper;
import ir.aliloc.api.config.MessageConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

public class MyAccessDeniedHandler implements AccessDeniedHandler {

    public static final Logger LOGGER = LoggerFactory.getLogger(MyAccessDeniedHandler.class);

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        httpServletResponse.setStatus(HttpStatus.FORBIDDEN.value());

        Map<String, Object> data = new HashMap<>();
        data.put("statusCode", HttpStatus.FORBIDDEN.value());
        data.put("message", MessageConstant.ERROR_ACCESS_DENIED_HANDLER);
        data.put("result", httpServletRequest.getRequestURL().toString());

        LOGGER.error("statusCode :"+ HttpStatus.FORBIDDEN.value() +",,, message : "+ MessageConstant.ERROR_ACCESS_DENIED_HANDLER + " ,result : " + httpServletRequest.getRequestURL().toString());
        OutputStream out = httpServletResponse.getOutputStream();
        com.fasterxml.jackson.databind.ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(out, data);
        out.flush();
    }
}
