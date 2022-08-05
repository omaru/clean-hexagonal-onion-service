package nl.codecentric.clean_hexagonal_onion_service.command.author;

import nl.codecentric.clean_hexagonal_onion_service.domain.book.Genre;

/**
 * @author Maik Kingma
 */

public record WriteBookDTO(String title, Genre genre) {
}
