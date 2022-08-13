package nl.codecentric.clean_hexagonal_onion_service.datasource.jpa.book;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.codecentric.clean_hexagonal_onion_service.datasource.Mapper;
import nl.codecentric.clean_hexagonal_onion_service.domain.book.Book;
import nl.codecentric.clean_hexagonal_onion_service.domain.book.BookService;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final Mapper<Book, BookJPA> mapper;

    @Override
    public void saveBook(final Book book) {
        bookRepository.save(mapper.toPersistence(book));
    }
}
