package tiv.exchange.external;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import tiv.exchange.models.Currency;

@Service
public class CurrencyProxy {

    @Autowired
    private CurrencyRatesServiceProxy currencyRates;

    @Cacheable(cacheNames = "currencyRates")
    public Currency getCurrencies() {
        return currencyRates.retrieveCurrencyRates();
    }
}
