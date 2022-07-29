package nl.codecentric.clean_hexagonal_onion_service.domain.author;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import nl.codecentric.clean_hexagonal_onion_service.domain.book.Book;
import nl.codecentric.clean_hexagonal_onion_service.domain.book.Genre;

/**
 * @author Maik Kingma
 */

@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Author {

    @Getter
    private Long id;

    @Getter
    private String firstName;

    @Getter
    private String lastName;

    public static Author createAuthor(String firstName, String lastName) {
        return new Author(null, firstName, lastName);
    }

    public Book writeManuscript(String title, Genre genre) {
        return Book.createManuscript(title, genre, this);
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }
}
