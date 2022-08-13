package nl.codecentric.clean_hexagonal_onion_service.usecase.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import nl.codecentric.clean_hexagonal_onion_service.domain.Genre;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class WriteBookRequest {
    private Long authorId;
    private WriteBookDTO book;

    public record WriteBookDTO(String title, Genre genre) {
    }

}
