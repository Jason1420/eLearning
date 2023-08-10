package com.elearning.exception;

import com.elearning.exception.helper.Result;
import com.elearning.exception.helper.StatusCode;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionHandlerAdvice {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleInvalidArgument(MethodArgumentNotValidException ex) {
        Map<String, String> errorMap = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errorMap.put(error.getField(), error.getDefaultMessage());
        });
        return errorMap;
    }

    @ExceptionHandler(Exception404.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    Result handlerException404(Exception404 ex, WebRequest req) {
        return new Result(false, StatusCode.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(Exception401.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    Result handlerException401(Exception401 ex, WebRequest req) {
        return new Result(false, StatusCode.UNAUTHORIZED, ex.getMessage());
    }

    @ExceptionHandler(Exception400.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    Result handlerException400(Exception400 ex, WebRequest req) {
        return new Result(false, StatusCode.INVALID_ARGUMENT, ex.getMessage());
    }

    @ExceptionHandler(Exception403.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    Result handlerException403(Exception404 ex, WebRequest req) {
        return new Result(false, StatusCode.FORBIDDEN, ex.getMessage());
    }

    @ExceptionHandler(Exception409.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    Result handlerException409(Exception409 ex, WebRequest req) {
        return new Result(false, StatusCode.CONFLICT, ex.getMessage());
    }

    @ExceptionHandler({AuthenticationException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    Result handleAuthenticationException(Exception ex) {
        return new Result(false, StatusCode.UNAUTHORIZED, "Username or password invalid");
    }

    @ExceptionHandler(AuthenticationCredentialsNotFoundException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Result handleInvalidBearerTokenException(AuthenticationException ex) {
        return new Result(false, StatusCode.UNAUTHORIZED, "The access token provided is expired, revoked, malformed, or invalid for other reasons");
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public Result handleAccessDeniedException(AccessDeniedException ex) {
        return new Result(false, StatusCode.FORBIDDEN, "No permission.");
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    Result handleException(Exception ex) {
        return new Result(false, StatusCode.INTERNAL_SERVER_ERROR, "INTERNAL SERVER ERROR");
    }

}