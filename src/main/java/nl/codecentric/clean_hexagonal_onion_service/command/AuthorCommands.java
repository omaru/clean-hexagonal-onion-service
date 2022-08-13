package nl.codecentric.clean_hexagonal_onion_service.command;

import lombok.RequiredArgsConstructor;
import nl.codecentric.clean_hexagonal_onion_service.domain.Author;
import nl.codecentric.clean_hexagonal_onion_service.domain.AuthorService;
import org.apache.coyote.Response;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.function.EntityResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/authors/commands/")
public class AuthorCommands {
    private final AuthorService authorService;

    @PostMapping(value = "register", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity registerAuthor(@RequestBody RegisterAuthorDTO registerAuthorDTO) {
        final Author author = Author.createAuthor(registerAuthorDTO.getFirstName(), registerAuthorDTO.getLastName());
        authorService.registerAuthor(author);
        return ResponseEntity.accepted().build();
    }

}
