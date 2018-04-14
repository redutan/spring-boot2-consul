package com.example.wiki.page;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 위키 페이지 Repository
 */
public interface PageRepository extends JpaRepository<Page, Long> {
    List<Page> findAllByParentPageIsNull();
}
