package nl.codecentric.clean_hexagonal_onion_service.query;

import lombok.Value;
import nl.codecentric.clean_hexagonal_onion_service.domain.author.Author;

/**
 * @author Maik Kingma
 */

@Value
public class AuthorView {
    public AuthorView(Author author) {
        this.id = author.getId();
        this.name = author.getFullName();
    }

    Long id;
    String name;
}
