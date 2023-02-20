package es.prices.infrastructure.rest;

import es.prices.domain.Price;
import es.prices.domain.services.GetPricesByFilterUseCaseService;
import es.prices.infrastructure.rest.contracts.PricesControllerContract;
import es.prices.infrastructure.rest.dto.PriceDto;
import es.prices.infrastructure.rest.dto.PricesRequestDto;
import es.prices.infrastructure.rest.exceptions.EmptyRequestException;
import es.prices.infrastructure.rest.mappers.PricesDtoMapper;
import es.prices.infrastructure.rest.mappers.PricesRequestDtoMapper;
import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping(value = "/prices", produces = MediaType.APPLICATION_JSON_VALUE)
@Api
public class PricesController implements PricesControllerContract {

    private final GetPricesByFilterUseCaseService getPricesByFilterUseCaseService;

    private final PricesRequestDtoMapper pricesRequestDtoMapper;

    private final PricesDtoMapper pricesDtoMapper;

    public PricesController(GetPricesByFilterUseCaseService getPricesByFilterUseCaseService, PricesRequestDtoMapper pricesRequestDtoMapper, PricesDtoMapper pricesDtoMapper) {
        this.getPricesByFilterUseCaseService = getPricesByFilterUseCaseService;
        this.pricesRequestDtoMapper = pricesRequestDtoMapper;
        this.pricesDtoMapper = pricesDtoMapper;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PriceDto> getPrices(@Valid @RequestBody PricesRequestDto pricesRequestDto) {

        if (validRequest(pricesRequestDto)) {
            List<Price> prices = getPricesByFilterUseCaseService.execute(
                    pricesRequestDtoMapper.mapToDomain(pricesRequestDto)
            );
            return pricesDtoMapper.mapToDtoList(prices);
        } else {
            throw new EmptyRequestException("All request parameter are empty.");
        }
    }

    private boolean validRequest(PricesRequestDto pricesRequestDto) {
        return !(pricesRequestDto.getApplyDate() == null && pricesRequestDto.getBrandId() == null && pricesRequestDto.getProductId() == null);
    }

}
