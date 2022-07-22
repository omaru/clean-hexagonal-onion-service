package nl.codecentric.clean_hexagonal_onion_service.command;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.codecentric.clean_hexagonal_onion_service.datasource.AuthorJPA;
import nl.codecentric.clean_hexagonal_onion_service.datasource.AuthorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AuthorCommandsTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private AuthorRepository authorRepository;

    @BeforeEach
    void beforeAll() {
        authorRepository.deleteAll();
    }

    @Test
    void registerAndGet() throws Exception {
        //given
        var registerAuthorDTOJson = objectMapper.writeValueAsString(new RegisterAuthorDTO("firstName", "lastName2"));
        var expected = AuthorJPA.builder().firstName("firstName").lastName("lastName2").build();
        //when
        mockMvc.perform(post("/authors/commands/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(registerAuthorDTOJson))
                .andExpect(status().isAccepted());
        authorRepository.flush();
        // then
        assertThat(authorRepository.findAll().size()).isEqualTo(1);
        assertThat(authorRepository.findAll().get(0)).usingRecursiveComparison().ignoringFields("id").isEqualTo(expected);
    }
}