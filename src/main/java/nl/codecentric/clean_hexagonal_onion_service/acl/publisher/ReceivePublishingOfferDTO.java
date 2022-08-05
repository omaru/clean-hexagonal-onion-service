package nl.codecentric.clean_hexagonal_onion_service.acl.publisher;

import java.util.UUID;

/**
 * @author Maik Kingma
 */

public record ReceivePublishingOfferDTO(UUID publisherId, String author, String title) {
}
