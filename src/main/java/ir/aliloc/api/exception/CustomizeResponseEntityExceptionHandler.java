/**
 * 3/7/2018
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.exception;


import ir.aliloc.api.config.MessageConstant;
import ir.aliloc.api.core.models.init.MainModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.ws.rs.ForbiddenException;
import javax.ws.rs.NotAcceptableException;
import javax.ws.rs.NotAllowedException;
import javax.ws.rs.NotFoundException;
import java.net.SocketTimeoutException;

@ControllerAdvice
@RestController
public class CustomizeResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger logger = LogManager.getLogger(CustomizeResponseEntityExceptionHandler.class);

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<MainModel> handleUserNotFoundException(UserNotFoundException ex) {
        logger.error(ex, ex);
        MainModel<String> mainModel = new MainModel<>(HttpStatus.NOT_FOUND.value(), ex.getMessage(), null);
        return new ResponseEntity<>(mainModel, HttpStatus.OK);
    }

    @ExceptionHandler(Exception.class)
    public <T> ResponseEntity<MainModel<T>> handleAllExceptions(Exception ex) {
        logger.error(ex, ex);
        if (ex instanceof AccessDeniedException) {
            MainModel mainModel = new MainModel<>(HttpStatus.FORBIDDEN.value(), ex.getMessage(), null);
            return new ResponseEntity<MainModel<T>>(mainModel, HttpStatus.OK);
        }
        if (ex instanceof NotAllowedException) {
            MainModel mainModel = new MainModel<>(HttpStatus.METHOD_NOT_ALLOWED.value(), ex.getMessage(), null);
            return new ResponseEntity<MainModel<T>>(mainModel, HttpStatus.OK);
        }
        if(ex instanceof NotFoundException){
            MainModel mainModel = new MainModel<>(HttpStatus.NOT_FOUND.value(),ex.getMessage(),null);
            return new ResponseEntity<MainModel<T>>(mainModel, HttpStatus.OK);
        }
        if(ex instanceof NotAcceptableException){
            MainModel mainModel = new MainModel<>(HttpStatus.NOT_ACCEPTABLE.value(),ex.getMessage(),null);
            return new ResponseEntity<MainModel<T>>(mainModel, HttpStatus.OK);
        }
        if(ex instanceof  UserNotFoundException){
            MainModel mainModel = new MainModel<>(HttpStatus.NOT_FOUND.value(),ex.getMessage(),null);
            return new ResponseEntity<MainModel<T>>(mainModel, HttpStatus.OK);
        }
        if(ex instanceof ForbiddenException){
            MainModel mainModel = new MainModel<>(HttpStatus.FORBIDDEN.value(),ex.getMessage(),null);
            return new ResponseEntity<MainModel<T>>(mainModel, HttpStatus.OK);
        }
        if(ex instanceof SocketTimeoutException){
            MainModel mainModel = new MainModel<>(HttpStatus.FORBIDDEN.value(), ex.getMessage(),null);
            return new ResponseEntity<MainModel<T>>(mainModel, HttpStatus.OK);
        }
        ex.printStackTrace();
        MainModel mainModel = new MainModel<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), MessageConstant.ERROR_INTERNAL_SERVER, null);
        return new ResponseEntity<MainModel<T>>(mainModel, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<MainModel> handleAccessDeniedException(Exception ex) {
        logger.error(ex, ex);
        MainModel<String> mainModel = new MainModel<>(HttpStatus.FORBIDDEN.value(), ex.getMessage(), null);
        return new ResponseEntity<MainModel>(mainModel, HttpStatus.OK);
    }


    @ExceptionHandler(SecurityException.class)
    public ResponseEntity<MainModel> handleSecurityException(Exception ex) {
        MainModel<String> mainModel = new MainModel<>(HttpStatus.FORBIDDEN.value(), ex.getMessage(), null);
        return new ResponseEntity<MainModel>(mainModel, HttpStatus.OK);
    }

    @ExceptionHandler(NotAllowedException.class)
    public ResponseEntity<MainModel> handleNotAllowedException(Exception ex) {
        MainModel<String> mainModel = new MainModel<>(HttpStatus.NOT_ACCEPTABLE.value(), ex.getMessage(), null);
        return new ResponseEntity<MainModel>(mainModel, HttpStatus.OK);
    }

    /*@ExceptionHandler(Exception.class)
    public ResponseEntity<RestMessage> handleExceptions(Exception ex, Locale locale) {
        String errorMessage = messageSource.getMessage(UNEXPECTED_ERROR, null e);
        ex.printStackTrace();
        return new ResponseEntity<>(new RestMessage(errorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
    }*/


}
