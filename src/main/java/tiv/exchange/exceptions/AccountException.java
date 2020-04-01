package tiv.exchange.exceptions;

public class AccountException extends Exception {

    public AccountException(final String message){
        super(message);
    }

    public AccountException(final String message, final Exception ex){
        super(message, ex);
    }
}
