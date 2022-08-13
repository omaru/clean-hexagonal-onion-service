package nl.codecentric.clean_hexagonal_onion_service.datasource;

import nl.codecentric.clean_hexagonal_onion_service.domain.Author;

public class AuthorMapper {
    public static AuthorJPA mapToJPA(final Author author) {
        return AuthorJPA.builder().firstName(author.getFirstName()).lastName(author.getLastName()).build();
    }

    public static Author mapToDomain(final AuthorJPA authorJPA) {
        return Author.builder().id(authorJPA.getId()).firstName(authorJPA.getFirstName()).lastName(authorJPA.getLastName()).build();
    }
}
