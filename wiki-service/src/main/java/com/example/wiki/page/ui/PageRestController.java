package com.example.wiki.page.ui;

import com.example.wiki.page.Page;
import com.example.wiki.page.application.PageService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/pages")
public class PageRestController {
    private final PageService pageService;

    public PageRestController(PageService pageService) {
        this.pageService = pageService;
    }

    @GetMapping
    public List<Page> getTopPages() {
        return pageService.getTopPages();
    }
}
