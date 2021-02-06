package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.Article;
import com.mycompany.myapp.service.ArticleService;
import io.swagger.annotations.ApiResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class ArticleResource {
    private final ArticleService articleService;

    public ArticleResource(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/v1/metadata/article/page")
    public Page<Article> getArticleByPage(@RequestParam Pageable pageable ){
      Page<Article>  getArticlePage = (Page<Article>) articleService.getAllArticleByPage(pageable);
      return getArticlePage;
    }
    @GetMapping("/v1/metadata/article/articleUUID")
    Optional<Article> getArticleByUUID(@RequestParam UUID articleUUID){
        Optional<Article> getArticleUUID = articleService.getByArticleUUID(articleUUID);
        return getArticleUUID;
    }
}
