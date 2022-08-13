package nl.codecentric.clean_hexagonal_onion_service.query;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nl.codecentric.clean_hexagonal_onion_service.domain.author.Author;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthorView {
    private Long id;
    private String name;

    public AuthorView(final Author author) {
        this.id = author.getId();
        this.name = author.getFirstName() + " " + author.getLastName();
    }

}