package nl.codecentric.clean_hexagonal_onion_service.domain.book;

import java.util.List;

/**
 * @author Maik Kingma
 */

public interface BookService {
    void storeManuscript(Book book);

    List<Book> findAll();

    List<Book> findByPartialTitle(String title);
}
