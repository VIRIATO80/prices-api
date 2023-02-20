package es.prices.domain.services.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class GetPricesRequest {
    private Date applyDate;
    private Integer productId;
    private Integer brandId;
}
