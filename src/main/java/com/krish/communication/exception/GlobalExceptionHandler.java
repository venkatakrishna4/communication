package com.krish.communication.exception;

import com.krish.communication.datamodel.ApiError;
import com.krish.communication.utils.Utils;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(value = UserNotFoundException.class)
  public ResponseEntity<ApiError> handleUserNotFoundException(UserNotFoundException ex) {
    ApiError apiError =
        ApiError.builder()
            .id(Utils.generateUUID())
            .message(ex.getMessage())
            .subErrors(ex.getSubErrors())
            .build();
    return ResponseEntity.badRequest().body(apiError);
  }

  @ExceptionHandler(value = CommunicationNotFoundException.class)
  public ResponseEntity<ApiError> handleCommunicationNotFoundException(
      CommunicationNotFoundException ex) {
    ApiError apiError =
        ApiError.builder().id(Utils.generateUUID()).message(ex.getMessage()).build();
    return ResponseEntity.badRequest().body(apiError);
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
      MethodArgumentNotValidException ex,
      HttpHeaders headers,
      HttpStatusCode status,
      WebRequest request) {
    List<String> subErrors = new ArrayList<>();
    ex.getFieldErrors().forEach(fieldError -> subErrors.add(fieldError.getDefaultMessage()));
    ApiError apiError =
        ApiError.builder()
            .id(Utils.generateUUID())
            .message(ex.getMessage())
            .subErrors(subErrors)
            .build();
    return ResponseEntity.badRequest().body(apiError);
  }

  public ResponseEntity<ApiError> handleUserNotVerifiedException(UserNotVerifiedException ex) {
    ApiError apiError =
        ApiError.builder()
            .id(Utils.generateUUID())
            .message(ex.getMessage())
            .subErrors(ex.getSubErrors())
            .build();
    return new ResponseEntity<>(apiError, HttpStatus.PRECONDITION_FAILED);
  }

  @ExceptionHandler(value = Exception.class)
  public ResponseEntity<ApiError> handleAllExceptions(Exception ex) {
    ApiError apiError =
        ApiError.builder().id(Utils.generateUUID()).message(ex.getMessage()).build();
    return ResponseEntity.internalServerError().body(apiError);
  }
}
