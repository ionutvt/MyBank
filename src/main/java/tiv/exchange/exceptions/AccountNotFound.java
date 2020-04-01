package tiv.exchange.exceptions;

public class AccountNotFound extends AccountException {

    public AccountNotFound(final String message){
        super(message);
    }

    public AccountNotFound(final String message, final Exception ex){
        super(message, ex);
    }
}
