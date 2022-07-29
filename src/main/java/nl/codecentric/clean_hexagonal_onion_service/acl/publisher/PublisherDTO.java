package nl.codecentric.clean_hexagonal_onion_service.acl.publisher;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.UUID;

/**
 * @author Maik Kingma
 */

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PublisherDTO {
    private UUID id;
    private String name;
}
