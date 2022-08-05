package nl.codecentric.clean_hexagonal_onion_service.domain.publisher;

import lombok.Builder;
import lombok.Value;
import nl.codecentric.clean_hexagonal_onion_service.domain.book.Book;

import java.util.UUID;

/**
 * @author Maik Kingma
 */

@Value
@Builder
public class Publisher {
    UUID id;
    String name;

    public void publishBook(Book book) {
        book.requestPublishing(id);
    }
}
