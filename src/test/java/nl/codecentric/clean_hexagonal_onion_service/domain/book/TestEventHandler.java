package nl.codecentric.clean_hexagonal_onion_service.domain.book;

import org.springframework.transaction.event.TransactionalEventListener;

interface TestEventHandler {
    @TransactionalEventListener()
    void handleEvent(Book.RequestPublishingEvent event);

}