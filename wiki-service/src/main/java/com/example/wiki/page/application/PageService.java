package com.example.wiki.page.application;

import com.example.wiki.page.Page;
import com.example.wiki.page.PageRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class PageService {
    private final PageRepository pageRepository;

    public PageService(PageRepository pageRepository) {
        this.pageRepository = pageRepository;
    }

    public List<Page> getTopPages() {
        return pageRepository.findAllByParentPageIsNull();

    }
}
