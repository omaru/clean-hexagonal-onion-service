package nl.codecentric.clean_hexagonal_onion_service.datasource;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.codecentric.clean_hexagonal_onion_service.domain.Author;
import nl.codecentric.clean_hexagonal_onion_service.domain.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;
    @Override
    public void registerAuthor(Author author) {
        log.info("AuthorServiceImpl::registerAuthor saving {}",author);
        authorRepository.save(AuthorMapper.mapToJPA(author));

    }

    @Override
    public List<Author> getAll() {
        return authorRepository.findAll().stream().map(AuthorMapper::mapToDomain).toList();
    }
}
