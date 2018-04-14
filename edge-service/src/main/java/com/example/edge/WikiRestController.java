package com.example.edge;

import com.example.edge.basic.RestTemplateWikiService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/wiki")
public class WikiRestController {
    private final WikiService wikiService;

    public WikiRestController(WikiService wikiService) {
        this.wikiService = wikiService;
    }

    @GetMapping("/pages")
    public List<Page> topPages() {
        return wikiService.getTopPages();
    }
}
