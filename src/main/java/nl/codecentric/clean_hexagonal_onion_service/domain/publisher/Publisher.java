package nl.codecentric.clean_hexagonal_onion_service.domain.publisher;

import lombok.Builder;
import lombok.Value;

import java.util.UUID;

/**
 * @author Maik Kingma
 */

@Value
@Builder
public class Publisher {
    UUID id;
    String name;
}
