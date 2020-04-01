package tiv.exchange.exceptions;

public class CurrencyNotFound extends AccountException {
    public CurrencyNotFound(String message) {
        super(message);
    }

    public CurrencyNotFound(String message, Exception ex) {
        super(message, ex);
    }
}
