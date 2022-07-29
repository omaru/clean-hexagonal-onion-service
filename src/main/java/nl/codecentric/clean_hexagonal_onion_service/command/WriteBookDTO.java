package nl.codecentric.clean_hexagonal_onion_service.command;

import nl.codecentric.clean_hexagonal_onion_service.domain.book.Genre;

/**
 * @author Maik Kingma
 */

public record WriteBookDTO(String title, Genre genre) {
}
