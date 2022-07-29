package nl.codecentric.clean_hexagonal_onion_service.datasource.book;

import nl.codecentric.clean_hexagonal_onion_service.datasource.author.AuthorJPA;
import nl.codecentric.clean_hexagonal_onion_service.datasource.author.AuthorMapper;
import nl.codecentric.clean_hexagonal_onion_service.domain.book.Book;

/**
 * @author Maik Kingma
 */

public class BookMapper {

    public static BookJPA mapToJPA(Book book) {
        return BookJPA.builder()
                .id(book.getId())
                .author(AuthorJPA.builder().id(book.getAuthor().getId()).build())
                .genre(book.getGenre())
                .title(book.getTitle())
                .published(book.isPublished())
                .publisherId(book.getPublisherId())
                .isbn(book.getIsbn())
                .build();
    }

    public static Book mapToDomain(BookJPA bookJPA) {
        return Book.builder()
                .id(bookJPA.getId())
                .title(bookJPA.getTitle())
                .author(AuthorMapper.mapToDomain(bookJPA.getAuthor()))
                .genre(bookJPA.getGenre())
                .isbn(bookJPA.getIsbn())
                .published(bookJPA.isPublished())
                .publisherId(bookJPA.getPublisherId())
                .build();
    }
}
