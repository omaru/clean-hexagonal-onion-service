package nl.codecentric.clean_hexagonal_onion_service.command.author;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterAuthorDTO {
    private String firstName;
    private String lastName;
}
