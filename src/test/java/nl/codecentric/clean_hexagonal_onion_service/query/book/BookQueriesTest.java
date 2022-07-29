package nl.codecentric.clean_hexagonal_onion_service.query.book;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import nl.codecentric.clean_hexagonal_onion_service.datasource.author.AuthorJPA;
import nl.codecentric.clean_hexagonal_onion_service.datasource.book.BookJPA;
import nl.codecentric.clean_hexagonal_onion_service.datasource.book.BookRepository;
import nl.codecentric.clean_hexagonal_onion_service.domain.book.Genre;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class BookQueriesTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    @Transactional
    void shouldFindBooksWithNoQueryParam() throws Exception {
        // given
        entityManager.createNativeQuery(
                        "INSERT INTO author (id, first_name, last_name) VALUES (?,?,?)")
                .setParameter(1, 1)
                .setParameter(2, "firstName")
                .setParameter(3, "lastName")
                .executeUpdate();
        var book1 = BookJPA.builder()
                .author(AuthorJPA.builder().id(1L).build())
                .genre(Genre.HORROR)
                .title("horror-book")
                .build();
        var book2 = BookJPA.builder()
                .author(AuthorJPA.builder().id(1L).build())
                .genre(Genre.ROMANCE)
                .title("romance-book")
                .build();
        var expectedBookView1 = new BookView("horror-book", "firstName lastName", null);
        var expectedBookView2 = new BookView("romance-book", "firstName lastName", null);

        bookRepository.saveAll(List.of(book1, book2));
        bookRepository.flush();
        // when
        MvcResult result = mockMvc.perform(get("/books")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        // then
        var resultingBookViews = objectMapper.readValue(
                result.getResponse().getContentAsString(), new TypeReference<List<BookView>>() {});
        assertThat(resultingBookViews).hasSize(2);
        assertThat(resultingBookViews).usingRecursiveFieldByFieldElementComparatorIgnoringFields("id")
                .containsExactlyInAnyOrder(expectedBookView1, expectedBookView2);
    }

    @Test
    @Transactional
    void shouldFindBooksFilteredByQueryParamTitle() throws Exception {
        // given
        entityManager.createNativeQuery(
                        "INSERT INTO author (id, first_name, last_name) VALUES (?,?,?)")
                .setParameter(1, 1)
                .setParameter(2, "firstName")
                .setParameter(3, "lastName")
                .executeUpdate();
        var book1 = BookJPA.builder()
                .author(AuthorJPA.builder().id(1L).build())
                .genre(Genre.HORROR)
                .title("horror-book")
                .build();
        var book2 = BookJPA.builder()
                .author(AuthorJPA.builder().id(1L).build())
                .genre(Genre.ROMANCE)
                .title("romance-book")
                .build();
        var expectedBookView = new BookView("horror-book", "firstName lastName", null);

        bookRepository.saveAll(List.of(book1, book2));
        bookRepository.flush();
        // when
        MvcResult result = mockMvc.perform(get("/books")
                    .accept(MediaType.APPLICATION_JSON)
                    .queryParam("title", "orror-"))
                .andExpect(status().isOk())
                .andReturn();
        // then
        var resultingBookViews = objectMapper.readValue(
                result.getResponse().getContentAsString(), new TypeReference<List<BookView>>() {});
        assertThat(resultingBookViews).hasSize(1);
        assertThat(resultingBookViews).usingRecursiveFieldByFieldElementComparatorIgnoringFields("id")
                .containsExactly(expectedBookView);
    }
}