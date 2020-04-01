package tiv.exchange.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import tiv.exchange.models.Currency;

@Service
@FeignClient(name= "currencyRatesServiceProxy", url = "https://api.exchangeratesapi.io")
public interface CurrencyRatesServiceProxy {

    @GetMapping("/latest")
    Currency retrieveCurrencyRates();
}
