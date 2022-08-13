package nl.codecentric.clean_hexagonal_onion_service.datasource.jpa.author;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<AuthorJPA, Long> {
}
