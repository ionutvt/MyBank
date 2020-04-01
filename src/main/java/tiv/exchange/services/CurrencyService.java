package tiv.exchange.services;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.interceptor.SimpleKey;
import org.springframework.stereotype.Service;
import tiv.exchange.external.CurrencyProxy;
import tiv.exchange.models.Currency;
import java.util.Objects;

@Service
public class CurrencyService {

    private CacheManager cacheManager;
    private CurrencyProxy currencyProxy;

    @Autowired
    public CurrencyService(CacheManager cacheManager, CurrencyProxy currencyProxy) {
        this.cacheManager = cacheManager;
        this.currencyProxy = currencyProxy;
    }

    @HystrixCommand(fallbackMethod = "putCurrencyInCache")
    public Currency getCurrenciesFromCache() {
        final Currency currency = cacheManager.getCache("currencyRates").get(SimpleKey.EMPTY, Currency.class);
        if (Objects.isNull(currency)) {
            throw new RuntimeException("Rates not found in cache.");
        }
        return currency;
    }

    private Currency putCurrencyInCache(){
        return currencyProxy.getCurrencies();
    }


}
