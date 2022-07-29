package nl.codecentric.clean_hexagonal_onion_service.query.book;

import lombok.AllArgsConstructor;
import lombok.Value;
import nl.codecentric.clean_hexagonal_onion_service.domain.book.Book;

/**
 * @author Maik Kingma
 */

@Value
@AllArgsConstructor
public class BookView {

    public BookView(Book book) {
        this.title = book.getTitle();
        this.author = book.getAuthor().getFullName();
        this.isbn = book.getIsbn();
    }

    String title;
    String author;
    String isbn;
}
