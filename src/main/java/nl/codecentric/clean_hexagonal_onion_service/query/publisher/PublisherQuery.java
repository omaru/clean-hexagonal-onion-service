package nl.codecentric.clean_hexagonal_onion_service.query.publisher;

import nl.codecentric.clean_hexagonal_onion_service.domain.publisher.Publisher;
import nl.codecentric.clean_hexagonal_onion_service.domain.publisher.PublisherService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Maik Kingma
 */

@RestController
@RequestMapping(value = "/publishers")
public class PublisherQuery {

    private final PublisherService publisherService;

    public PublisherQuery(PublisherService publisherService) {
        this.publisherService = publisherService;
    }

    @GetMapping
    List<Publisher> getAllPublishers() {
        return publisherService.getAllPublishers();
    }
}
