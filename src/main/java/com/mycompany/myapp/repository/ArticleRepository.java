package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Article;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

@Repository
public interface ArticleRepository extends JpaRepository<Article, UUID>  {
    Page<Article> findAll(Pageable pageable);
}
