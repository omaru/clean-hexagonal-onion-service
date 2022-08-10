package nl.codecentric.clean_hexagonal_onion_service.datasource.book;

import lombok.extern.slf4j.Slf4j;
import nl.codecentric.clean_hexagonal_onion_service.domain.book.Book;
import nl.codecentric.clean_hexagonal_onion_service.domain.book.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Maik Kingma
 */

@Slf4j
@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void storeManuscript(Book book) {
        bookRepository.save(BookMapper.mapToJPA(book));
        book.clearDomainEvents();
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll().stream().map(BookMapper::mapToDomain).collect(Collectors.toList());
    }

    @Override
    public List<Book> findByPartialTitle(String title) {
        return bookRepository.findByTitleContains(title).stream().map(BookMapper::mapToDomain).collect(Collectors.toList());
    }

    @Override
    public Book findById(Long bookId) {
        return bookRepository.findById(bookId)
                .map(BookMapper::mapToDomain)
                .orElseThrow(() -> new BookNotFoundException(String.format("Book with id %d could not be found!", bookId)));
    }
}
