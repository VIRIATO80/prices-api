package es.prices.infrastructure.dao.mappers;

import es.prices.domain.Price;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring")
public interface PriceEntityMapper {

    List<Price> mapToDomainList(List<es.prices.infrastructure.dao.entities.Price> prices);
}
