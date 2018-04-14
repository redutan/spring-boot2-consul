package com.example.edge.feign;

import com.example.edge.Page;
import com.example.edge.WikiService;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Profile("feign")
@Primary
public class FeignWikiService implements WikiService {
    private final WikiClient wikiClient;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    public FeignWikiService(WikiClient wikiClient) {
        this.wikiClient = wikiClient;
    }

    @Override
    public List<Page> getTopPages() {
        return wikiClient.getTopPages();
    }

    @SuppressWarnings("WeakerAccess")
    public Page getNotFoundPage() {
        return wikiClient.getNotFoundPage();
    }
}
