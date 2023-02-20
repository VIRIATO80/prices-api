package es.prices.infrastructure.dao.repositories;

import es.prices.domain.services.request.GetPricesRequest;
import es.prices.infrastructure.dao.entities.Price;

import java.util.List;

public interface PricesRepositoryCustom {

    List<Price> findPricesByFilter(GetPricesRequest getPricesRequest);
}
