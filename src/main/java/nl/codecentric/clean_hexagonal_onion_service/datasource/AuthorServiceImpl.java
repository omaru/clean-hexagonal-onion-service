package nl.codecentric.clean_hexagonal_onion_service.datasource;

import lombok.extern.slf4j.Slf4j;
import nl.codecentric.clean_hexagonal_onion_service.domain.author.Author;
import nl.codecentric.clean_hexagonal_onion_service.domain.author.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Maik Kingma
 */

@Slf4j
@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public void registerAuthor(Author author) {
        log.info("registering author: " + author.getFullName());
        authorRepository.save(AuthorMapper.mapToJPA(author));
    }

    @Override
    public List<Author> findAll() {
        return authorRepository.findAll().stream()
                .map(AuthorMapper::mapToDomain)
                .collect(Collectors.toList());
    }
}
