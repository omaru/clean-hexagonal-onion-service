package nl.codecentric.clean_hexagonal_onion_service.process;

import nl.codecentric.clean_hexagonal_onion_service.domain.book.Book;
import nl.codecentric.clean_hexagonal_onion_service.process.book.PublishBookDelegate;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.UUID;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringJUnitConfig
@SpringBootTest
class EventProcessorTest {

    @Mock
    private PublishBookDelegate publishBookDelegate;

    @InjectMocks
    private EventProcessor eventProcessor;

    @Test
    void shouldCallTheDelegateToActOnEvent() {
        // when
        Book.RequestPublishingEvent requestPublishingEvent = new Book.RequestPublishingEvent(1L, UUID.randomUUID());
        eventProcessor.handleEvent(requestPublishingEvent);
        // then
        verify(publishBookDelegate, times(1)).publishBook(requestPublishingEvent);
    }
}