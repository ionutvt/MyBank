package tiv.exchange;

import com.google.code.beanmatchers.BeanMatchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import static org.hamcrest.MatcherAssert.assertThat;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import tiv.exchange.external.CurrencyProxy;
import tiv.exchange.models.Account;
import tiv.exchange.models.Currency;
import tiv.exchange.services.CurrencyService;
import java.math.BigDecimal;
import java.time.LocalDate;
import static org.hamcrest.CoreMatchers.allOf;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Random;

import static com.google.code.beanmatchers.BeanMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ExchangeApplicationTests {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private CacheManager cacheManager;
    @MockBean
    private CurrencyService currencyService;
    @SpyBean
    private CurrencyProxy currencyProxy;

    @Test
    void accountNotFound() throws Exception {
        mockMvc.perform(get("/accounts/{accountId}", 44L))
                .andExpect(status().isNotFound());
    }

    @Test
    void accountFoundRatesCached() throws Exception {
        doReturn(getCurrencyMock()).when(currencyService).getCurrenciesFromCache();
        mockMvc.perform(get("/accounts/{accountId}", 1L))
                .andExpect(status().is2xxSuccessful());
        evictAllCaches();
        currencyProxy.getCurrencies();
        currencyProxy.getCurrencies();
        verify(currencyProxy, times(1)).getCurrencies();
    }

    @Test
    void accountFoundRatesNotCached() throws Exception {
        doReturn(getCurrencyMock()).when(currencyService).getCurrenciesFromCache();
        mockMvc.perform(get("/accounts/{accountId}", 1L))
                .andExpect(status().is2xxSuccessful());
        evictAllCaches();
        currencyProxy.getCurrencies();
        verify(currencyProxy, atLeast(1)).getCurrencies();
    }

    @Test
    void testDtoMapping() {
        BeanMatchers.registerValueGenerator(LocalDateTime::now, LocalDateTime.class);
        BeanMatchers.registerValueGenerator(() -> LocalDate.now().minusDays(Math.abs(new Random().nextLong()) % 100), LocalDate.class);
        assertThat(Account.class, allOf(hasValidBeanConstructor(), hasValidBeanEquals(), hasValidGettersAndSetters(),
                hasValidBeanHashCode(), hasValidBeanToString()));
        assertThat(Currency.class, allOf(hasValidBeanConstructor(), hasValidBeanEquals(), hasValidGettersAndSetters(),
                hasValidBeanHashCode(), hasValidBeanToString()));
    }

    @Test
    void fullIntegrationTest() throws Exception {
        doReturn(getCurrencyMock()).when(currencyService).getCurrenciesFromCache();
        System.out.println(currencyService.getCurrenciesFromCache());
        mockMvc.perform(get("/accounts/{accountId}", 1L))
                .andExpect(status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.jsonPath("$.balance").value(1000));
    }

    private Object getCurrencyMock() {
        final Currency currency = new Currency();
        currency.setBase("EUR");
        currency.setDate(LocalDate.now());
        currency.setRates(Map.of("RON", BigDecimal.TEN));
        return currency;
    }

    private void evictAllCaches() {
        cacheManager.getCacheNames().stream()
                .map(name -> cacheManager.getCache(name))
                .forEach(Cache::clear);
    }
}