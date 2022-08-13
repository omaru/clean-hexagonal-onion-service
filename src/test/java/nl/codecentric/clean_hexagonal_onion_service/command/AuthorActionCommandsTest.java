package nl.codecentric.clean_hexagonal_onion_service.command;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.codecentric.clean_hexagonal_onion_service.datasource.jpa.author.AuthorJPA;
import nl.codecentric.clean_hexagonal_onion_service.datasource.jpa.author.AuthorRepository;
import nl.codecentric.clean_hexagonal_onion_service.datasource.jpa.book.BookJPA;
import nl.codecentric.clean_hexagonal_onion_service.datasource.jpa.book.BookRepository;
import nl.codecentric.clean_hexagonal_onion_service.domain.Genre;
import nl.codecentric.clean_hexagonal_onion_service.usecase.request.WriteBookRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

import static org.assertj.core.api.BDDAssertions.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AuthorActionCommandsTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private EntityManager entityManager;

    @BeforeEach
    void beforeAll() {
        authorRepository.deleteAll();
        bookRepository.deleteAll();
    }

    @Test
    @Transactional
    void writeBook() throws Exception {
        // given
        entityManager.createNativeQuery(
                        "INSERT INTO author (id, first_name, last_name) VALUES (?,?,?)")
                .setParameter(1, 1)
                .setParameter(2, "firstName")
                .setParameter(3, "lastName")
                .executeUpdate();

        var writeBookDTOJson = objectMapper.writeValueAsString(new WriteBookRequest.WriteBookDTO("title", Genre.CRIME));
        var expected = BookJPA.builder()
                .title("title")
                .genre(Genre.CRIME)
                .published(false)
                .author(AuthorJPA.builder().id(1L).build())
                .build();
        // when
        mockMvc.perform(post("/authors/1/commands/writeBook")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(writeBookDTOJson))
                .andExpect(status().isAccepted());
        bookRepository.flush();
        // then
        List<BookJPA> books = bookRepository.findAll();
        then(books).usingRecursiveComparison().ignoringFields("id").isEqualTo(List.of(expected));
    }
}