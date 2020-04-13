package tiv.exchange.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tiv.exchange.exceptions.AccountException;
import tiv.exchange.models.Account;
import tiv.exchange.validators.CurrencyValidator;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

@Service
public class ConverterService {

    private CurrencyService currencyService;
    private CurrencyValidator currencyValidator;

    @Autowired
    public ConverterService(final CurrencyService currencyService, final CurrencyValidator currencyValidator){
        this.currencyService = currencyService;
        this.currencyValidator = currencyValidator;
    }

    public BigDecimal getBalanceEur(final Account account) throws AccountException {
        final Map<String, BigDecimal> rates = currencyService.getCurrenciesFromCache().getRates();
        currencyValidator.validateCurrency(rates, account.getCurrency());
        return account.getBalance().divide(rates.get(account.getCurrency()), 2, RoundingMode.HALF_DOWN);
    }
}