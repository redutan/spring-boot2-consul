package com.example.edge.feign;

import com.example.edge.Page;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.*;

/**
 * consul, wiki-service가 정상적으로 기동된 상황에서 실행 가능한 통합테스트
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@ActiveProfiles("feign")
public class FeignWikiServiceIntegTest {
    @Autowired
    FeignWikiService feignWikiService;

    @Test
    public void getTopPages() {
        List<Page> pages = feignWikiService.getTopPages();
        assertThat(pages, hasSize(3));
        pages.forEach(this::assertPage);
    }

    private void assertPage(Page page) {
        assertThat(page.getPageId(), is(notNullValue()));
        assertThat(page.getTitle(), is(notNullValue()));
        assertThat(page.getContent(), is(notNullValue()));
    }

    @Test   // fallback test
    public void getNotFoundPage_fallback() {
        Page page = feignWikiService.getNotFoundPage();
        assertThat(page, is(Page.EMPTY));
    }
}