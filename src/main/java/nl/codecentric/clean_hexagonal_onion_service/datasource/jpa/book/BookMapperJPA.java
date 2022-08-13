package nl.codecentric.clean_hexagonal_onion_service.datasource.jpa.book;

import lombok.RequiredArgsConstructor;
import nl.codecentric.clean_hexagonal_onion_service.datasource.Mapper;
import nl.codecentric.clean_hexagonal_onion_service.datasource.jpa.author.AuthorJPA;
import nl.codecentric.clean_hexagonal_onion_service.domain.author.Author;
import nl.codecentric.clean_hexagonal_onion_service.domain.book.Book;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class BookMapperJPA implements Mapper<Book, BookJPA> {
    private final Mapper<Author, AuthorJPA> authorJPAMapper;

    @Override
    public BookJPA toPersistence(Book domain) {
        return BookJPA.builder()
                .id(domain.getId())
                .author(AuthorJPA.builder().id(domain.getAuthor().getId()).build())
                .genre(domain.getGenre())
                .title(domain.getTitle())
                .published(domain.getPublished())
                .isbn(domain.getIsbn())
                .build();
    }

    @Override
    public Book toDomain(BookJPA persistence) {
        return Book.builder()
                .id(persistence.getId())
                .title(persistence.getTitle())
                .author(authorJPAMapper.toDomain(persistence.getAuthor()))
                .genre(persistence.getGenre())
                .isbn(persistence.getIsbn())
                .published(persistence.getPublished())
                .publisherId(persistence.getPublisherId())
                .build();
    }
}

