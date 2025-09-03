package finalmission.domain.auth.exception;

import finalmission.infrastructure.exception.AuthorizationException;

public class AccessDeniedException extends AuthorizationException {
    public AccessDeniedException(final String message) {
        super(message);
    }
}
