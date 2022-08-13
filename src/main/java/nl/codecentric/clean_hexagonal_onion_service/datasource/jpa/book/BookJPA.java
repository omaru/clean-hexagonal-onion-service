package nl.codecentric.clean_hexagonal_onion_service.datasource.jpa.book;

import lombok.*;
import nl.codecentric.clean_hexagonal_onion_service.datasource.jpa.author.AuthorJPA;
import nl.codecentric.clean_hexagonal_onion_service.domain.Genre;

import javax.persistence.*;

import java.util.UUID;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Builder
@Getter
@NoArgsConstructor()
@AllArgsConstructor
@Table(name = "book")
public class BookJPA {
    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "book_seq_gen")
    @SequenceGenerator(name = "book_seq_gen", sequenceName = "book_seq", allocationSize = 1)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private AuthorJPA author;
    private String title;
    private Genre genre;
    private UUID publisherId;
    private Boolean published;
    private String isbn;
}
