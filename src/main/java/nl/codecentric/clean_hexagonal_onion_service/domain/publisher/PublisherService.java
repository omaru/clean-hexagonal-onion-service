package nl.codecentric.clean_hexagonal_onion_service.domain.publisher;

import java.util.List;
import java.util.UUID;

/**
 * @author Maik Kingma
 */

public interface PublisherService {
    List<Publisher> getAllPublishers();

    Publisher getPublisherById(UUID publisherId);

    String requestPublishing(UUID publisherId, String fullName, String title);
}
