package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.Article;
import com.mycompany.myapp.repository.ArticleRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class ArticleService {
    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }
    public Object getAllArticleByPage(Pageable pageable) {
        Page<Article> articlesPage = articleRepository.findAll(pageable);
        return articlesPage;
    }
    public Optional<Article> getByArticleUUID(UUID articleUUID) {
        Optional<Article> articleByUUID = articleRepository.findById(articleUUID);
        return articleByUUID;
        }
}
