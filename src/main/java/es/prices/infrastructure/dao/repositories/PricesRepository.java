package es.prices.infrastructure.dao.repositories;

import es.prices.infrastructure.dao.entities.Price;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PricesRepository extends JpaRepository<Price, Integer>, PricesRepositoryCustom {
}
