package nl.codecentric.clean_hexagonal_onion_service.datasource;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
}
