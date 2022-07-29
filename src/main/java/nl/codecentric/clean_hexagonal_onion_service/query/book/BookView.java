package nl.codecentric.clean_hexagonal_onion_service.query.book;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nl.codecentric.clean_hexagonal_onion_service.domain.author.Author;
import nl.codecentric.clean_hexagonal_onion_service.domain.book.Book;

/**
 * @author Maik Kingma
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookView {

    public BookView(Book book, Author bookAuthor) {
        this.title = book.getTitle();
        this.author = bookAuthor.getFullName();
        this.isbn = book.getIsbn();
    }

    String title;
    String author;
    String isbn;
}
