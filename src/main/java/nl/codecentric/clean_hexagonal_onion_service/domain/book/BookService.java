package nl.codecentric.clean_hexagonal_onion_service.domain.book;

import java.util.List;

/**
 * @author Maik Kingma
 */

public interface BookService {
    void storeManuscript(Book book);

    List<Book> findAll();

    List<Book> findByPartialTitle(String title);

    Book findById(Long bookId);

    class BookNotFoundException  extends RuntimeException{
        public BookNotFoundException(String errorMessage) {
            super(errorMessage);
        }
    }

    class BookAlreadyInPublishingException  extends RuntimeException{
        public BookAlreadyInPublishingException(String errorMessage) {
            super(errorMessage);
        }
    }
}
