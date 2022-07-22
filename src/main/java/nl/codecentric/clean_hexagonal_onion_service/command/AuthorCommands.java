package nl.codecentric.clean_hexagonal_onion_service.command;

import nl.codecentric.clean_hexagonal_onion_service.domain.author.Author;
import nl.codecentric.clean_hexagonal_onion_service.domain.author.AuthorService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Maik Kingma
 */

@RestController
@RequestMapping("authors/commands")
public class AuthorCommands {

    private final AuthorService authorService;

    public AuthorCommands(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping("/register")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public void create(@RequestBody RegisterAuthorDTO registerAuthorDTO) {
        var author = Author.createAuthor(registerAuthorDTO.firstName(), registerAuthorDTO.lastName());
        authorService.registerAuthor(author);

    }
}
