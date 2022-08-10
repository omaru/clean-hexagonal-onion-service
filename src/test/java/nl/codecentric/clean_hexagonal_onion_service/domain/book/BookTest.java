package nl.codecentric.clean_hexagonal_onion_service.domain.book;

import nl.codecentric.clean_hexagonal_onion_service.datasource.author.AuthorRepository;
import nl.codecentric.clean_hexagonal_onion_service.domain.author.Author;
import nl.codecentric.clean_hexagonal_onion_service.domain.author.AuthorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.UUID;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringJUnitConfig
@SpringBootTest
class BookTest {

    @MockBean
    private TestEventHandler eventHandler;

    @Autowired
    private BookService bookService;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private AuthorRepository authorRepository;

    @Test
    void shouldPublishEventOnSavingAggregate() {
        var author = Author.builder().firstName("first").lastName("last").build();
        authorService.registerAuthor(author);
        authorRepository.flush();

        var persistedAuthor = authorService.findAll().get(0);

        var book = Book.builder().id(1L).author(persistedAuthor).published(false).genre(Genre.HORROR).title("title").build();
        UUID publisherId = UUID.randomUUID();
        book.requestPublishing(publisherId);
        bookService.storeManuscript(book);
        verify(eventHandler, times(1)).handleEvent(new Book.RequestPublishingEvent(1L, publisherId));
    }

    @Test
    void shouldPublishEventOnSavingAggregateOnlyOnce() {
        var author = Author.builder().firstName("first").lastName("last").build();
        authorService.registerAuthor(author);
        authorRepository.flush();

        var persistedAuthor = authorService.findAll().get(0);

        var book = Book.builder().id(1L).author(persistedAuthor).published(false).genre(Genre.HORROR).title("title").build();
        UUID publisherId = UUID.randomUUID();
        book.requestPublishing(publisherId);
        bookService.storeManuscript(book);
        bookService.storeManuscript(book);
        verify(eventHandler, times(1)).handleEvent(new Book.RequestPublishingEvent(1L, publisherId));
    }
}