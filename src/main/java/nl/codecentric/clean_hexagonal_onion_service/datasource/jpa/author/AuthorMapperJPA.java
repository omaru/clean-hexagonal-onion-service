package nl.codecentric.clean_hexagonal_onion_service.datasource.jpa.author;

import nl.codecentric.clean_hexagonal_onion_service.datasource.Mapper;
import nl.codecentric.clean_hexagonal_onion_service.domain.author.Author;
import org.springframework.stereotype.Component;

@Component
public class AuthorMapperJPA implements Mapper<Author, AuthorJPA> {
    @Override
    public AuthorJPA toPersistence(final Author author) {
        return AuthorJPA.builder().firstName(author.getFirstName()).lastName(author.getLastName()).build();
    }

    @Override
    public Author toDomain(final AuthorJPA persistence) {
        return Author.createAuthor(persistence.getId(), persistence.getFirstName(), persistence.getLastName());
    }

}
