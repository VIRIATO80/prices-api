package es.prices.infrastructure.rest.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageError {
    private String code;
    private String description;
}
