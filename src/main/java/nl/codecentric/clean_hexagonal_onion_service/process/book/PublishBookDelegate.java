package nl.codecentric.clean_hexagonal_onion_service.process.book;

import nl.codecentric.clean_hexagonal_onion_service.domain.book.Book;
import nl.codecentric.clean_hexagonal_onion_service.domain.book.BookService;
import nl.codecentric.clean_hexagonal_onion_service.domain.publisher.PublisherService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Maik Kingma
 */

@Service
public class PublishBookDelegate {

    private final BookService bookService;
    private final PublisherService publisherService;


    public PublishBookDelegate(BookService bookService, PublisherService publisherService) {
        this.bookService = bookService;
        this.publisherService = publisherService;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void publishBook(Book.RequestPublishingEvent event) {
        var book = bookService.findById(event.getBookId());
        var isbn = publisherService.requestPublishing(event.getPublisherId(), book.getAuthor().getFullName(),
                book.getTitle());

        book.updatePublishingInfo(isbn);

        bookService.storeManuscript(book);
    }
}
