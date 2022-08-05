package nl.codecentric.clean_hexagonal_onion_service.acl.publisher;

import nl.codecentric.clean_hexagonal_onion_service.domain.publisher.Publisher;
import nl.codecentric.clean_hexagonal_onion_service.domain.publisher.PublisherService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.json.Json;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author Maik Kingma
 */

@Service
public class PublisherServiceImpl implements PublisherService {

    private static final String SUB_PATH_PUBLISHERS = "/publishers";
    private static final String SUB_PATH_PUBLISHERS_ID = "/publishers/%s";
    private static final String SUB_PATH_PUBLISHERS_RECEIVE_BOOK_OFFER = "/publishers/receiveBookOffer";

    private final RestTemplate restTemplate = new RestTemplate();
    
    @Value("${publisher.service.host}")
    private String publisherServiceBaseUri;

    @Override
    public List<Publisher> getAllPublishers() {
        String uri = getUri(SUB_PATH_PUBLISHERS);
        var result = restTemplate.getForObject(uri, PublisherDTO[].class);
        return Arrays.stream(Objects.requireNonNull(result))
                .map(publisherDTO -> Publisher.builder().id(publisherDTO.getId()).name(publisherDTO.getName()).build())
                .collect(Collectors.toList());
    }

    @Override
    public Publisher getPublisherById(UUID publisherId) {
        String uri = getUri(String.format(SUB_PATH_PUBLISHERS_ID, publisherId.toString()));
        var result = restTemplate.getForObject(uri, PublisherDTO.class);
        return Publisher.builder().id(Objects.requireNonNull(result).getId()).name(result.getName()).build();
    }

    @Override
    public String requestPublishing(UUID publisherId, String fullName, String title) {
        var uri = getUri(SUB_PATH_PUBLISHERS_RECEIVE_BOOK_OFFER);
        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        var payload = Json.createObjectBuilder()
                .add("publisherId", publisherId.toString())
                .add("author", fullName)
                .add("title", title)
                .build().toString();
        var request = new HttpEntity<>(payload, headers);
        PublishingResponseDTO publishingResponseDTO = restTemplate.postForObject(uri, request, PublishingResponseDTO.class);

        return publishingResponseDTO.isbn();
    }

    private String getUri(String subPath) {
        return publisherServiceBaseUri + subPath;
    }
}
