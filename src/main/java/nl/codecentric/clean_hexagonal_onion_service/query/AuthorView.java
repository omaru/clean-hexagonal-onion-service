package nl.codecentric.clean_hexagonal_onion_service.query;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nl.codecentric.clean_hexagonal_onion_service.domain.author.Author;

/**
 * @author Maik Kingma
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthorView {
    public AuthorView(Author author) {
        this.id = author.getId();
        this.name = author.getFullName();
    }

    Long id;
    String name;
}
