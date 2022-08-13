package nl.codecentric.clean_hexagonal_onion_service.domain.author;

import lombok.*;
import nl.codecentric.clean_hexagonal_onion_service.domain.book.Book;
import nl.codecentric.clean_hexagonal_onion_service.domain.Genre;

@ToString
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Author {
    private Long id;
    private String firstName;
    private String lastName;

    public static Author createAuthor(final String firstName, final String lastName) {
        return new Author(null, firstName, lastName);
    }

    public static Author createAuthor(final Long id, final String firstName, final String lastName) {
        return new Author(id, firstName, lastName);
    }

    public Book createBook(String title, Genre genre) {
        return Book.createBook(title, genre, this);
    }

}
