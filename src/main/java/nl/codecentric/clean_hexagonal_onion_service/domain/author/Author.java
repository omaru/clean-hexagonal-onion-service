package nl.codecentric.clean_hexagonal_onion_service.domain.author;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

/**
 * @author Maik Kingma
 */

@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Author {

    @Getter
    private Long id;

    @Getter
    private String firstName;

    @Getter
    private String lastName;

    public static Author createAuthor(String firstName, String lastName) {
        return new Author(null, firstName, lastName);
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }
}
