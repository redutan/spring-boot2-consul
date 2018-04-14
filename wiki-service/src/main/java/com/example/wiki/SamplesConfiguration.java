package com.example.wiki;

import com.example.wiki.page.Page;
import com.example.wiki.page.PageRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;

/**
 * 샘플 데이터를 미리 입력해주는 구성
 */
@Profile("samples")
@Configuration
public class SamplesConfiguration {
    private final PageRepository pageRepository;

    public SamplesConfiguration(PageRepository pageRepository) {
        this.pageRepository = pageRepository;
    }

    @Bean
    CommandLineRunner addSamplePages() {
        return args -> pageRepository.saveAll(Arrays.asList(
                Page.forTopPage("기획팀 Home", "기획팀 대시보드 페이지"),
                Page.forTopPage("개발팀 Home", "개발팀 대시보드 페이지"),
                Page.forTopPage("디자인팀 Home", "디자인팀 대시보드 페이지")
        ));
    }
}
