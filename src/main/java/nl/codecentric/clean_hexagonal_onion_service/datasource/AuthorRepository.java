package nl.codecentric.clean_hexagonal_onion_service.datasource;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Maik Kingma
 */

@Repository
public interface AuthorRepository extends JpaRepository<AuthorJPA, Long> {
}
