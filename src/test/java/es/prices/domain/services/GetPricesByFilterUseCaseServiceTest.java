package es.prices.domain.services;

import es.prices.domain.services.request.GetPricesRequest;
import es.prices.infrastructure.dao.entities.Price;
import es.prices.infrastructure.dao.repositories.PricesRepositoryCustom;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;


@RunWith(SpringRunner.class)
@SpringBootTest
public class GetPricesByFilterUseCaseServiceTest {

    @Autowired
    private GetPricesByFilterUseCaseService getPricesByFilterUseCaseService;

    @MockBean(PricesRepositoryCustom.class)
    PricesRepositoryCustom pricesRepositoryCustom;

    private  GetPricesRequest getPricesRequest;

    private List<Price> priceList = new ArrayList<>();


    @Before
    public void preTest() {
        // Mock response
        Price price = new Price();
        price.setBrandId(1);
        price.setProductId(1);
        Price price2 = new Price();
        price2.setBrandId(1);
        price2.setProductId(2);
        price2.setPriority(0);
        price2.setPrice(new BigDecimal(100));
        Price price3 = new Price();
        price3.setBrandId(1);
        price3.setProductId(2);
        price3.setPriority(1);
        price3.setPrice(new BigDecimal(200));

        priceList.add(price);
        priceList.add(price2);
        priceList.add(price3);
    }

    @Test
    public void findByBrandReturnResults() {
        //Request
        getPricesRequest = new GetPricesRequest();
        getPricesRequest.setBrandId(1);
        given(pricesRepositoryCustom.findPricesByFilter(getPricesRequest)).willReturn(priceList);
        List<es.prices.domain.Price> responsePrices = getPricesByFilterUseCaseService.execute(getPricesRequest);
        assertTrue(responsePrices.get(0).getBrandId() == 1);
    }

    @Test
    public void findByBrandNotExistsReturnEmpty() {
        //Request
        getPricesRequest = new GetPricesRequest();
        getPricesRequest.setBrandId(2);
        given(pricesRepositoryCustom.findPricesByFilter(getPricesRequest)).willReturn(new ArrayList<>());
        List<es.prices.domain.Price> responsePrices = getPricesByFilterUseCaseService.execute(getPricesRequest);
        assertTrue(responsePrices.isEmpty());
    }

    @Test
    public void returnHigherPriority() {
        //Request
        getPricesRequest = new GetPricesRequest();
        getPricesRequest.setBrandId(1);
        getPricesRequest.setApplyDate(new Date());

        given(pricesRepositoryCustom.findPricesByFilter(getPricesRequest)).willReturn(priceList);
        List<es.prices.domain.Price> responsePrices = getPricesByFilterUseCaseService.execute(getPricesRequest);
        assertTrue(responsePrices.size() == 2);
        assertTrue(responsePrices.get(1).getPriority() == 1);
        assertTrue(Objects.equals(responsePrices.get(1).getPrice(), new BigDecimal(200)));
    }

}
