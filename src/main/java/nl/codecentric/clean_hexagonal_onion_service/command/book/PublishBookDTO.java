package nl.codecentric.clean_hexagonal_onion_service.command.book;

import java.util.UUID;

/**
 * @author Maik Kingma
 */

public record PublishBookDTO(UUID publisherId) {
}
