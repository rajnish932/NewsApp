package com.mycompany.myapp.service;


import com.mycompany.myapp.domain.Article;
import exception.NewsAlreadyExistsException;
import exception.NewsNotFoundException;
import org.springframework.data.domain.Page;

public interface PopularWatchItemService {


    /**
     * @param news
     * @return true for successful save , else false
     * @throws NewsAlreadyExistsException
     */
    public boolean saveNews(Article news) throws NewsAlreadyExistsException, NewsAlreadyExistsException;
    /**
     * @param newsId
     * @return true for successful delete else false
     * @throws NewsNotFoundException
     */
    public boolean deleteNewsById(int newsId,String userId) throws NewsNotFoundException, NewsNotFoundException;

    /**
     * Get all news for user
     * @return List<News>
     */
    public Page<Article> getAllNews(String userId);

    /**
     * API to get news by title
     * @param searchText
     * @return
     */
    public Page<Article> searchByTitle(String userId, String searchText);

}
