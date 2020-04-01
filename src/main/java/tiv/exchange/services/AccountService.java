package tiv.exchange.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tiv.exchange.dao.AccountDao;
import tiv.exchange.exceptions.AccountException;
import tiv.exchange.exceptions.AccountNotFound;
import tiv.exchange.models.Account;

@Service
public class AccountService {

    private AccountDao accountDao;
    private ConverterService converterService;

    @Autowired
    public AccountService(final AccountDao accountDao, final ConverterService converterService) {
        this.accountDao = accountDao;
        this.converterService = converterService;
    }

    public Account findAccountById(final Long accountId) throws AccountException {
        final Account account = accountDao.findById(accountId)
                .orElseThrow(() -> new AccountNotFound("This account does not exist. Please check the input data."));
        account.setBalance(converterService.getBalanceEur(account));
        return account;
    }
}
