package es.prices.infraestructure.rest;

import es.prices.PricesApplication;
import es.prices.domain.Price;
import es.prices.domain.services.GetPricesByFilterUseCaseService;
import es.prices.domain.services.request.GetPricesRequest;
import es.prices.infrastructure.rest.PricesController;
import es.prices.infrastructure.rest.exceptions.ControlAdviceHandler;
import es.prices.infrastructure.rest.exceptions.EmptyRequestException;
import es.prices.infrastructure.rest.mappers.PricesDtoMapper;
import es.prices.infrastructure.rest.mappers.PricesRequestDtoMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(classes = PricesApplication.class)
@Import({ControlAdviceHandler.class})
public class PricesControllerTest {

    MockMvc mockMvc;

    @Mock
    PricesRequestDtoMapper pricesRequestDtoMapper;

    @Mock
    PricesDtoMapper pricesDtoMapper;

    @Mock
    private GetPricesByFilterUseCaseService getPricesByFilterUseCaseService;
    @InjectMocks
    private PricesController pricesController;
    private final String emptyBody = "{}";
    private final List<Price> emptyPricesList = new ArrayList<>();

    @BeforeEach
    public void preTest() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(pricesController)
                .build();
    }

    @Test
    public void responseEmptyTest() throws Exception {
        GetPricesRequest getPricesRequest = new GetPricesRequest();
        when(getPricesByFilterUseCaseService.execute(getPricesRequest)).thenReturn(emptyPricesList);
        String jsonBody = "{\"brandId\": \"1\", \"productId\": \"2\"}";
        mockMvc
                .perform(post("/prices/")
                        .content(jsonBody)
                        .contentType("application/json"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void responseEmptyExceptionTest() {
        assertThatThrownBy(() ->
                mockMvc.perform(post("/prices/")
                .content(emptyBody)
                .contentType("application/json"))
                .andDo(print())
                .andExpect(status().isBadRequest())).hasCause(new EmptyRequestException("All request parameter are empty."));
    }
}
