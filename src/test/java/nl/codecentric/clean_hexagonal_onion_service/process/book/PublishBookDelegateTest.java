package nl.codecentric.clean_hexagonal_onion_service.process.book;

import nl.codecentric.clean_hexagonal_onion_service.domain.author.Author;
import nl.codecentric.clean_hexagonal_onion_service.domain.author.AuthorService;
import nl.codecentric.clean_hexagonal_onion_service.domain.book.Book;
import nl.codecentric.clean_hexagonal_onion_service.domain.book.BookService;
import nl.codecentric.clean_hexagonal_onion_service.domain.book.Genre;
import nl.codecentric.clean_hexagonal_onion_service.domain.publisher.PublisherService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockserver.client.MockServerClient;
import org.mockserver.model.Header;
import org.mockserver.springtest.MockServerTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.json.Json;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockserver.matchers.Times.exactly;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

@MockServerTest
@SpringBootTest
class PublishBookDelegateTest {

    private static final Long BOOK_ID = 1L;
    private static final Long AUTHOR_ID = 2L;

    @Mock
    private AuthorService authorService;

    @Mock
    private BookService bookService;

    @Autowired
    private PublisherService publisherService;

    private MockServerClient mockServerClient;
    
    @Test
    void shouldCallThePublisherServiceAPIWithCorrectPayload() {
        PublishBookDelegate publishBookDelegate = new PublishBookDelegate(bookService, publisherService);
        UUID publisherUUID = UUID.randomUUID();
        UUID isbnUUID = UUID.randomUUID();
        configureMockPublishersReceiveBookOffer(isbnUUID.toString());

        var author = Author.builder().id(AUTHOR_ID).firstName("firstName").lastName("lastName").build();
        when(authorService.findById(AUTHOR_ID)).thenReturn(author);
        var book =
                Book.builder().id(BOOK_ID).published(false).publisherId(publisherUUID).genre(Genre.HORROR).title(
                        "title").author(author).build();
        when(bookService.findById(BOOK_ID)).thenReturn(book);

        // when
        publishBookDelegate.publishBook(new Book.RequestPublishingEvent(BOOK_ID, publisherUUID));
        // then
        mockServerClient.verify(request()
                .withPath("/publishers/receiveBookOffer")
                .withMethod("POST")
                .withBody(Json.createObjectBuilder()
                        .add("publisherId", publisherUUID.toString())
                        .add("author", "firstName lastName")
                        .add("title", "title")
                        .build().toString()));
        book.updatePublishingInfo(String.format("ISBN-%s", isbnUUID));
        verify(bookService, times(1)).storeManuscript(book);
    }

    private void configureMockPublishersReceiveBookOffer(String uuid) {
        var responseBody = Json.createObjectBuilder()
                .add("isbn", String.format("ISBN-%s", uuid))
                .build().toString();

        mockServerClient.when(request().withMethod("POST").withPath("/publishers/receiveBookOffer"), exactly(1)).respond(
                response()
                        .withStatusCode(202)
                        .withHeaders(new Header("Content-Type", "application/json; charset=utf-8"))
                        .withBody(responseBody)
                        .withDelay(TimeUnit.SECONDS,1)
        );
    }
}