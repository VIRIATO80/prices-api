package es.prices.infrastructure.rest.mappers;

import es.prices.domain.Price;
import es.prices.infrastructure.rest.dto.PriceDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PricesDtoMapper {

    List<PriceDto> mapToDtoList(List<Price> prices);
}
