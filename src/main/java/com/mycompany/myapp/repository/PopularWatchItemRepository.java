package com.mycompany.myapp.repository;


import java.util.List;
import java.util.UUID;

import com.mycompany.myapp.domain.Article;
import com.mycompany.myapp.domain.PopularWatchItem;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestParam;


/**
 *
 *	PopularWatchItemRepository  - User can watch, list,add,delete from watch list using this repo
 */
public interface PopularWatchItemRepository  extends JpaRepository<PopularWatchItem, UUID>{

    /**
     * API to find news by user id
     * @param news
     * @param userId
     * @return
     */
    PopularWatchItem findByNewsAndUserId(Article news, String userId);
    /**
     * API to fetch by news by id and user id
     * @param id
     * @param userId
     * @return
     */
    Page<PopularWatchItem> fetchByIdAndUserId (@RequestParam("id") int id, @RequestParam("userId") String userId);
    /**
     * API to get all popular news of a user
     * @param userId
     * @return
     */
    List<Article> getAllWatchListNews (@RequestParam("userId") String userId);


    /**
     * API to search news
     * @param userId
     * @param searchparam
     * @return
     */
    List<Article> searchNews (@RequestParam("userId") String userId, @RequestParam("searchparam") String searchparam);
}

