package nl.codecentric.clean_hexagonal_onion_service.domain.author;

import nl.codecentric.clean_hexagonal_onion_service.domain.book.Book;

import java.util.List;

public interface AuthorService {
    void registerAuthor(final Author author);

    List<Author> getAll();

    Author get(final Long authorId);

    void writeBook(final Long authorId, final Book book);
}
