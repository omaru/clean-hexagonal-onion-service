package nl.codecentric.clean_hexagonal_onion_service.domain.book;

import lombok.*;
import nl.codecentric.clean_hexagonal_onion_service.domain.Genre;
import nl.codecentric.clean_hexagonal_onion_service.domain.author.Author;

import java.util.UUID;

import static lombok.AccessLevel.*;

@ToString
@Builder
@Getter
@AllArgsConstructor(access = PRIVATE)
public class Book {
    private Long id;
    private Author author;
    private String title;
    private Genre genre;
    @Getter
    private UUID publisherId;
    private Boolean published;
    private String isbn;

    public static Book createBook(final String title, final Genre genre, final Author author) {
        return new Book(null, author, title, genre, null, false, null);
    }

}
