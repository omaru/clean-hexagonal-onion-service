package nl.codecentric.clean_hexagonal_onion_service.usecase;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.codecentric.clean_hexagonal_onion_service.usecase.request.WriteBookRequest;
import nl.codecentric.clean_hexagonal_onion_service.domain.author.AuthorService;
import nl.codecentric.clean_hexagonal_onion_service.domain.book.BookService;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class WriteBook implements UseCase<WriteBookRequest, Void> {
    private final AuthorService authorService;
    private final BookService bookService;

    @Override
    public Void execute(final WriteBookRequest request) {
        var author = authorService.get(request.getAuthorId());
        var book = author.createBook(request.getBook().title(), request.getBook().genre());
        bookService.saveBook(book);
        return null;
    }
}
