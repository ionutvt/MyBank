package tiv.exchange.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tiv.exchange.exceptions.AccountException;
import tiv.exchange.exceptions.CurrencyNotFound;
import tiv.exchange.models.Account;
import java.math.BigDecimal;
import java.util.Objects;

@Service
public class ConverterService {

    private CurrencyService currencyService;

    @Autowired
    public ConverterService(final CurrencyService currencyService){
        this.currencyService = currencyService;
    }

    public BigDecimal getBalanceEur(final Account account) throws AccountException {
        final BigDecimal rate = currencyService.getCurrenciesFromCache().getRates().get(account.getCurrency());
        if (Objects.isNull(rate)){
            throw new CurrencyNotFound("The account currency is not valid.");
        }
        return account.getBalance().multiply(rate);
    }
}
