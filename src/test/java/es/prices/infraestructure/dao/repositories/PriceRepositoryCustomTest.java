package es.prices.infraestructure.dao.repositories;

import es.prices.domain.services.request.GetPricesRequest;
import es.prices.infrastructure.dao.entities.Price;
import es.prices.infrastructure.dao.repositories.PricesRepositoryCustomImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@RunWith(SpringRunner.class)
@DataJpaTest
public class PriceRepositoryCustomTest {

    @Autowired
    private PricesRepositoryCustomImpl pricesRepositoryCustom;


    @Test
    public void whenFindByFilterReturnResults() {
        // given
        GetPricesRequest pricesRequest = new GetPricesRequest();
        pricesRequest.setProductId(35456);

        // when
        List<Price> foundList = pricesRepositoryCustom.findPricesByFilter(pricesRequest);

        // then
        assertThat(foundList.get(0).getProductId())
                .isEqualTo(35456);
    }
}
