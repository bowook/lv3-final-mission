package finalmission.domain.time.exception;

import finalmission.infrastructure.exception.UnprocessableException;

public class TimeNotAllowedException extends UnprocessableException {
    public TimeNotAllowedException(final String message) {
        super(message);
    }
}
