package es.prices.infrastructure.rest.exceptions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControlAdviceHandler {

    @Autowired
    private MessageErrorFactory messageErrorFactory;


    @ExceptionHandler(value = {EmptyRequestException.class})
    public ResponseEntity<MessageError> handleUnAuthorizedException(RuntimeException ex) {
        return new ResponseEntity<>(getMessageError(ex, HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
    }

    private MessageError getMessageError(RuntimeException runtimeException, HttpStatus status) {
        MessageError messageError = messageErrorFactory.getMessageErrorForMethodArgumentNotValid(runtimeException);
        messageError.setCode(Integer.toString(status.value()));
        return messageError;
    }

}
