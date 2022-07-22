package nl.codecentric.clean_hexagonal_onion_service.datasource;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import static javax.persistence.GenerationType.SEQUENCE;

/**
 * @author Maik Kingma
 */

@Entity
@Builder
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
