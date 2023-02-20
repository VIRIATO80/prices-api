package es.prices.infrastructure.rest.mappers;


import es.prices.domain.services.request.GetPricesRequest;
import es.prices.infrastructure.rest.dto.PricesRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PricesRequestDtoMapper {

    @Mapping(source = "applyDate", target = "applyDate", dateFormat = "dd/MM/yyyy")
    GetPricesRequest mapToDomain(PricesRequestDto pricesRequestDto);
}
