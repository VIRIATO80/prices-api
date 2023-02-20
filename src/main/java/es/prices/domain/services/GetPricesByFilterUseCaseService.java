package es.prices.domain.services;

import es.prices.domain.Price;
import es.prices.domain.services.request.GetPricesRequest;
import es.prices.infrastructure.dao.mappers.PriceEntityMapper;
import es.prices.infrastructure.dao.repositories.PricesRepositoryCustom;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Getter
@Setter
@Service
public class GetPricesByFilterUseCaseService {

    private final PricesRepositoryCustom pricesRepository;

    private final PriceEntityMapper priceEntityMapper;

    public GetPricesByFilterUseCaseService(PricesRepositoryCustom pricesRepository, PriceEntityMapper priceEntityMapper) {
        this.pricesRepository = pricesRepository;
        this.priceEntityMapper = priceEntityMapper;
    }

    public List<Price> execute(GetPricesRequest getPricesRequest) {
        List<Price> prices = priceEntityMapper.mapToDomainList(pricesRepository.findPricesByFilter(getPricesRequest));
        if (getPricesRequest.getApplyDate() == null) {
            return prices;
        } else {
            return filterByPriority(prices);
        }
    }

    private List<Price> filterByPriority(List<Price> prices) {
        return prices.stream()
                .collect(Collectors.groupingBy(Price::getProductId,
                        Collectors.maxBy(Comparator.comparing(Price::getPriority))))
                .values()
                .stream()
                .map(Optional::get)
                .collect(Collectors.toList());
    }
}
