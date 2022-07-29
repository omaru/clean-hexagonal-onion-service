package nl.codecentric.clean_hexagonal_onion_service.query.book;

import nl.codecentric.clean_hexagonal_onion_service.domain.book.BookService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Maik Kingma
 */

@RestController
@RequestMapping(value = "/books")
public class BookQueries {

    private final BookService bookService;

    public BookQueries(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<BookView> findBooks(@RequestParam(value = "title", required = false) String title) {
        if(title == null || title.isEmpty()) {
            return bookService.findAll().stream().map(BookView::new).collect(Collectors.toList());
        } else {
            return bookService.findByPartialTitle(title).stream().map(BookView::new).collect(Collectors.toList());
        }
    }
}
