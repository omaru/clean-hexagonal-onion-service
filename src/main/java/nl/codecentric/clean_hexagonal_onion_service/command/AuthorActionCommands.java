package nl.codecentric.clean_hexagonal_onion_service.command;

import nl.codecentric.clean_hexagonal_onion_service.domain.author.AuthorService;
import nl.codecentric.clean_hexagonal_onion_service.domain.book.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Maik Kingma
 */

@RestController
@RequestMapping("authors/{id}/commands")
public class AuthorActionCommands {

    private final AuthorService authorService;

    private final BookService bookService;

    public AuthorActionCommands(AuthorService authorService, BookService bookService) {
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @PostMapping("/writeBook")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public void writeBook(@PathVariable("id") Long authorId, @RequestBody WriteBookDTO writeBookDTO) {
        var author = authorService.findById(authorId);
        var book = author.writeManuscript(writeBookDTO.title(), writeBookDTO.genre());

        bookService.storeManuscript(book);

    }
}
