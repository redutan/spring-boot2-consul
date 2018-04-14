package com.example.edge;

import lombok.Data;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class EdgeServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EdgeServiceApplication.class, args);
    }

    @Bean
    @LoadBalanced
    RestTemplate loadBalancedRestTemplate() {
        return new RestTemplate();
    }
}

@RestController
@RequestMapping("/wiki")
class WikiRestController {
    private final WikiService wikiService;

    public WikiRestController(WikiService wikiService) {
        this.wikiService = wikiService;
    }

    @GetMapping("/pages")
    public List<Page> topPages() {
        return wikiService.getTopPages();
    }
}

@Service
class WikiService {
    private final RestTemplate restTemplate;

    public WikiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

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

@Data
class Page {
    private Long pageId;
    private String title;
    private String content;
}