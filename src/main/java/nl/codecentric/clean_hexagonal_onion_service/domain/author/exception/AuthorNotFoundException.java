package nl.codecentric.clean_hexagonal_onion_service.domain.author.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.text.MessageFormat;

public class AuthorNotFoundException extends ResponseStatusException {
    private static final String MESSAGE = "Author with id {0}  not found";

    public AuthorNotFoundException(final Long authorId) {
        super(HttpStatus.NOT_FOUND, MessageFormat.format(MESSAGE, authorId));
    }
}
