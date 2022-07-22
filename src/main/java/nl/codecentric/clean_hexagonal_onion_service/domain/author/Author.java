package nl.codecentric.clean_hexagonal_onion_service.domain.author;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

/**
 * @author Maik Kingma
 */

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Author {

    private Long id;

    private String firstName;

    private String lastName;

    public static Author createAuthor(String firstName, String lastName) {
        return new Author(null, firstName, lastName);
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }
}
