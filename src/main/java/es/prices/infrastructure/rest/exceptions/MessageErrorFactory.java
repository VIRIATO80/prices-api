package es.prices.infrastructure.rest.exceptions;

import org.springframework.stereotype.Component;

@Component
public class MessageErrorFactory {

    public MessageError getMessageErrorForMethodArgumentNotValid(RuntimeException runtimeException) {
        MessageError messageError = new MessageError();
        messageError.setDescription(runtimeException.getMessage());
        return messageError;
    }

}
