package nl.codecentric.clean_hexagonal_onion_service.datasource;

import nl.codecentric.clean_hexagonal_onion_service.domain.author.Author;

/**
 * @author Maik Kingma
 */

public class AuthorMapper {

    public static AuthorJPA mapToJPA(Author author) {
        return AuthorJPA.builder()
                .id(author.getId())
                .firstName(author.getFirstName())
                .lastName(author.getLastName())
                .build();
    }
}
