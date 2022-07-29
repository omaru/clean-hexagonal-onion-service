package nl.codecentric.clean_hexagonal_onion_service.acl.publisher;

import nl.codecentric.clean_hexagonal_onion_service.domain.publisher.Publisher;
import nl.codecentric.clean_hexagonal_onion_service.domain.publisher.PublisherService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author Maik Kingma
 */

@Service
public class PublisherServiceImpl implements PublisherService {

    private static final String SUB_PATH_PUBLISHERS = "/publishers";
    
    @Value("${publisher.service.host}")
    private String publisherServiceBaseUri;

    @Override
    public List<Publisher> getAllPublishers() {
        String uri=getUri(SUB_PATH_PUBLISHERS);
        RestTemplate restTemplate = new RestTemplate();
        var result = restTemplate.getForObject(uri, PublisherDTO[].class);
        return Arrays.stream(Objects.requireNonNull(result))
                .map(publisherDTO -> Publisher.builder().id(publisherDTO.getId()).name(publisherDTO.getName()).build())
                .collect(Collectors.toList());
    }

    private String getUri(String subPath) {
        return publisherServiceBaseUri + subPath;
    }
}
