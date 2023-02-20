package es.prices.infrastructure.rest.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class PriceDto {
    private Integer productId;
    private Integer brandId;
    private Integer priority;
    private BigDecimal price;
    private Date startDate;
    private Date endDate;
}