package nl.codecentric.clean_hexagonal_onion_service.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@Builder
public class Author {
    private Long id;
    private String firstName;
    private String lastName;

    public static Author createAuthor(String firstName, String lastName) {
        return Author.builder().firstName(firstName).lastName(lastName).build();
    }
}
