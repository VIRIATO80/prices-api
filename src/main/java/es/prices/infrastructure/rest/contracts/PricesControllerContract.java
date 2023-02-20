package es.prices.infrastructure.rest.contracts;

import es.prices.infrastructure.rest.dto.PriceDto;
import es.prices.infrastructure.rest.dto.PricesRequestDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

public interface PricesControllerContract {
    @ApiOperation(
            nickname = "Get prices by filter",
            tags = {"Prices"},
            response = List.class,
            value = "Get prices by filter"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Success", response = List.class),
                    @ApiResponse(code = 400, message = "Bad Request"),
                    @ApiResponse(code = 401, message = "Unauthorized"),
                    @ApiResponse(code = 403, message = "Forbidden"),
                    @ApiResponse(code = 500, message = "Failure")
            }
    )
    List<PriceDto> getPrices(@Valid @RequestBody PricesRequestDto pricesRequestDto);


}
