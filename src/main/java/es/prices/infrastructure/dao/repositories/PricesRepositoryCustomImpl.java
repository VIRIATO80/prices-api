package es.prices.infrastructure.dao.repositories;

import es.prices.domain.services.request.GetPricesRequest;
import es.prices.infrastructure.dao.entities.Price;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PricesRepositoryCustomImpl implements PricesRepositoryCustom {

    private final EntityManager em;

    public PricesRepositoryCustomImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Price> findPricesByFilter(GetPricesRequest getPricesRequest) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Price> cq = cb.createQuery(Price.class);

        Root<Price> price = cq.from(Price.class);
        List<Predicate> predicates = new ArrayList<>();

        if (getPricesRequest.getBrandId() != null) {
            predicates.add(cb.equal(price.get("brandId"), getPricesRequest.getBrandId()));
        }
        if (getPricesRequest.getProductId() != null) {
            predicates.add(cb.equal(price.get("productId"), getPricesRequest.getProductId()));
        }

        if (getPricesRequest.getApplyDate() != null) {
            predicates.add(cb.lessThanOrEqualTo(price.get("startDate"), getPricesRequest.getApplyDate()));
            predicates.add(cb.greaterThanOrEqualTo(price.get("endDate"), getPricesRequest.getApplyDate()));
        }

        cq.where(predicates.toArray(new Predicate[0]));
        return em.createQuery(cq).getResultList();
    }
}
