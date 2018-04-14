package com.example.edge.basic;

import com.example.edge.Page;
import com.example.edge.WikiService;
import org.springframework.context.annotation.Profile;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Profile("default")
@Service
public class RestTemplateWikiService implements WikiService {
    private final RestTemplate restTemplate;

    public RestTemplateWikiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<Page> getTopPages() {
        return restTemplate.exchange(
                "//wiki-service/pages",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Page>>() {
                })
                .getBody();
    }
}
