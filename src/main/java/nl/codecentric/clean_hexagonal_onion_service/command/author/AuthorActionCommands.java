package nl.codecentric.clean_hexagonal_onion_service.command.author;

import lombok.RequiredArgsConstructor;
import nl.codecentric.clean_hexagonal_onion_service.usecase.UseCase;
import nl.codecentric.clean_hexagonal_onion_service.usecase.request.WriteBookRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/authors/{id}/commands")
@RequiredArgsConstructor
public class AuthorActionCommands {
    private final UseCase<WriteBookRequest, Void> writeBook;

    @PostMapping("/writeBook")
    public ResponseEntity writeBook(@PathVariable("id") final Long authorId,
                                    @RequestBody final WriteBookRequest.WriteBookDTO writeBookDTO) {
        writeBook.execute(new WriteBookRequest(authorId, writeBookDTO));
        return ResponseEntity.accepted().build();
    }
}