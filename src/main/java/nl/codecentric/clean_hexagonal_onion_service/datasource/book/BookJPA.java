package nl.codecentric.clean_hexagonal_onion_service.datasource.book;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import nl.codecentric.clean_hexagonal_onion_service.datasource.author.AuthorJPA;
import nl.codecentric.clean_hexagonal_onion_service.domain.book.Genre;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import java.util.UUID;

import static javax.persistence.GenerationType.SEQUENCE;

/**
 * @author Maik Kingma
 */

@Entity
@Builder
@NoArgsConstructor()
@AllArgsConstructor
@Table(name = "book")
public class BookJPA {

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "book_seq_gen")
    @SequenceGenerator(name = "book_seq_gen", sequenceName = "book_seq", allocationSize = 1)
    @Getter
    private Long id;

    @Getter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private AuthorJPA author;

    @Getter
    private String title;

    @Getter
    @Enumerated(EnumType.STRING)
    private Genre genre;

    @Getter
    private UUID publisherId;

    @Getter
    private boolean published;

    @Getter
    private String isbn;
}
