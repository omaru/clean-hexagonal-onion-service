package nl.codecentric.clean_hexagonal_onion_service.process;

import lombok.extern.slf4j.Slf4j;
import nl.codecentric.clean_hexagonal_onion_service.domain.book.Book;
import nl.codecentric.clean_hexagonal_onion_service.process.book.PublishBookDelegate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

import static org.springframework.transaction.event.TransactionPhase.AFTER_COMMIT;

/**
 * @author Maik Kingma
 */

@Slf4j
@Component
public class EventProcessor {

    private final PublishBookDelegate publishBookDelegate;

    public EventProcessor(PublishBookDelegate publishBookDelegate) {
        this.publishBookDelegate = publishBookDelegate;
    }

    @TransactionalEventListener(phase = AFTER_COMMIT)
    public void handleEvent(Book.RequestPublishingEvent requestPublishingEvent) {
        log.info(requestPublishingEvent.toString());
        publishBookDelegate.publishBook(requestPublishingEvent);
    }
}
