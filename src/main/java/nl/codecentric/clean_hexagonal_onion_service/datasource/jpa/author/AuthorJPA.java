package nl.codecentric.clean_hexagonal_onion_service.datasource.jpa.author;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import nl.codecentric.clean_hexagonal_onion_service.datasource.jpa.book.BookJPA;

import javax.persistence.*;

import java.util.Set;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Builder
@Getter
@NoArgsConstructor()
@AllArgsConstructor
@Table(name = "author")
public class AuthorJPA {
    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "author_seq_gen")
    @SequenceGenerator(name = "author_seq_gen", sequenceName = "author_seq", allocationSize = 1)
    private Long id;
    private String firstName;
    private String lastName;
    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<BookJPA> books;

}
