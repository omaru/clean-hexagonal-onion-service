package nl.codecentric.clean_hexagonal_onion_service.datasource.jpa.author;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.codecentric.clean_hexagonal_onion_service.datasource.Mapper;
import nl.codecentric.clean_hexagonal_onion_service.datasource.jpa.book.BookJPA;
import nl.codecentric.clean_hexagonal_onion_service.datasource.jpa.book.BookRepository;
import nl.codecentric.clean_hexagonal_onion_service.domain.author.Author;
import nl.codecentric.clean_hexagonal_onion_service.domain.author.AuthorService;
import nl.codecentric.clean_hexagonal_onion_service.domain.author.exception.AuthorNotFoundException;
import nl.codecentric.clean_hexagonal_onion_service.domain.book.Book;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;
    private final Mapper<Author, AuthorJPA> authorMapper;
    private final Mapper<Book, BookJPA> bookMapper;

    private final BookRepository bookRepository;

    @Override
    public void registerAuthor(final Author author) {
        log.info("AuthorServiceImpl::registerAuthor saving {}", author);
        authorRepository.save(authorMapper.toPersistence(author));

    }

    @Override
    public void writeBook(final Long authorId, final Book book) {
        final Author author = this.get(authorId);
        var bookToSave = author.createBook(book.getTitle(), book.getGenre());
        bookRepository.save(bookMapper.toPersistence(bookToSave));

    }

    @Override
    public List<Author> getAll() {
        log.info("AuthorServiceImpl::getAll Getting authors");
        return authorRepository.findAll().stream().map(authorMapper::toDomain).toList();
    }

    @Override
    public Author get(final Long authorId) {
        log.info("AuthorServiceImpl::get Getting author {}", authorId);
        return authorRepository.findById(authorId).map(authorMapper::toDomain).orElseThrow(() -> new AuthorNotFoundException(authorId));
    }


}
