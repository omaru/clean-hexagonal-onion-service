package nl.codecentric.clean_hexagonal_onion_service.query;

import lombok.RequiredArgsConstructor;
import nl.codecentric.clean_hexagonal_onion_service.domain.author.AuthorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping(value = "/authors")
@RequiredArgsConstructor
public class ActorQueries {
    private final AuthorService authorService;
    @GetMapping
    public List<AuthorView> getAll() {
        return authorService.getAll().stream()
                .map(AuthorView::new)
                .collect(toList());
    }
}
