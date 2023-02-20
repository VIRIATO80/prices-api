package es.prices.infrastructure.rest.dto;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class PricesRequestDto {
    private Integer brandId;
    private String applyDate;
    private Integer productId;
}
