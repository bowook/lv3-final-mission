package finalmission.infrastructure.exception;

public class UnprocessableException extends RuntimeException {
    public UnprocessableException(final String message) {
        super(message);
    }
}
