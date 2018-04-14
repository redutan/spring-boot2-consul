package com.example.edge.feign;

import com.example.edge.Page;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collections;
import java.util.List;

@Profile("feign")
@FeignClient(name = "wiki-service", fallbackFactory = WikiClientFallbackFactory.class)
public interface WikiClient {

    @GetMapping("/pages")
    List<Page> getTopPages();

    @GetMapping("/no-resources")
    Page getNotFoundPage();
}

@Profile("feign")
@Component
@Slf4j
class WikiClientFallbackFactory implements FallbackFactory<WikiClient> {
    @Override
    public WikiClient create(Throwable ex) {
        return new WikiClient() {
            @Override
            public List<Page> getTopPages() {
                log.warn("WikiClient#getTopPages fallback", ex);
                return Collections.emptyList();
            }

            @Override
            public Page getNotFoundPage() {
                log.warn("WikiClient#getNotFoundPage fallback", ex);
                return Page.EMPTY;
            }
        };
    }
}
