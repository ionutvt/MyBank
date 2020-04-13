package tiv.exchange.validators;

import org.springframework.stereotype.Component;
import tiv.exchange.exceptions.CurrencyNotFound;

import java.math.BigDecimal;
import java.util.Map;

@Component
public class CurrencyValidator {

    public void validateCurrency(final Map<String, BigDecimal> currencies, final String currencyId) throws CurrencyNotFound {
        if (!currencies.containsKey(currencyId)){
            throw new CurrencyNotFound("The account currency is not valid.");
        }
    }
}
