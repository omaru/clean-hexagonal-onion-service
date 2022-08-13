package nl.codecentric.clean_hexagonal_onion_service.datasource.jpa.book;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<BookJPA, Long> {
    List<BookJPA> findByTitleContains(String title);
}
