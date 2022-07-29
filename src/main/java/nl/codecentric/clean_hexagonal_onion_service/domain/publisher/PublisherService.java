package nl.codecentric.clean_hexagonal_onion_service.domain.publisher;

import java.util.List;

/**
 * @author Maik Kingma
 */

public interface PublisherService {
    List<Publisher> getAllPublishers();
}
