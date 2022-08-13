package nl.codecentric.clean_hexagonal_onion_service.domain;

import java.util.List;

public interface AuthorService {
    void registerAuthor(Author author);
    List<Author> getAll();
}
