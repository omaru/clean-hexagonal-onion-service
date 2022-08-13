package nl.codecentric.clean_hexagonal_onion_service.query;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import nl.codecentric.clean_hexagonal_onion_service.datasource.AuthorJPA;
import nl.codecentric.clean_hexagonal_onion_service.datasource.AuthorRepository;
import nl.codecentric.clean_hexagonal_onion_service.domain.Author;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.assertj.core.api.BDDAssertions.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AuthorQueriesTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private AuthorRepository authorRepository;

    @BeforeEach
    void setUp() {
        authorRepository.deleteAll();
    }

    @Test
    void getAll() throws Exception {
        // given
        var authorJPA = AuthorJPA.builder().firstName("firstName").lastName("lastName").build();
        authorRepository.save(authorJPA);
        authorRepository.flush();
        AuthorView expected = new AuthorView(Author.createAuthor("firstName", "lastName"));
        // when then
        MvcResult result = mockMvc.perform(get("/authors")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        var resultingAuthorViews = objectMapper.readValue(
                result.getResponse().getContentAsString(), new TypeReference<List<AuthorView>>() {
                });
        then(resultingAuthorViews).usingRecursiveFieldByFieldElementComparatorIgnoringFields("id").containsExactly(expected);

    }
}