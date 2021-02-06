package com.mycompany.myapp.service.imp;

import com.mycompany.myapp.domain.Article;
import com.mycompany.myapp.domain.PopularWatchItem;
import com.mycompany.myapp.repository.PopularWatchItemRepository;
import com.mycompany.myapp.service.PopularWatchItemService;
import exception.NewsAlreadyExistsException;
import exception.NewsNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import java.awt.print.Pageable;

public class PopularWatchItemServiceImp implements PopularWatchItemService {

    final String testUserId = "testid";

    @Autowired
    private PopularWatchItemRepository watchListRepo;
    @Override
    public boolean saveNews(Article news) throws NewsAlreadyExistsException {
        //String userId= testUserId;
        if (news.getArticleUUID() != null) {
            final PopularWatchItem object = watchListRepo.findByNewsAndUserId(news,news.getArticleUUID().toString());
            if (object != null) {
                throw new NewsAlreadyExistsException("Could not save news, News already exists");
            }
        }
        watchListRepo.save(new PopularWatchItem(news,news.getArticleUUID().toString()));
        return true;
    }

    @Override
    public boolean deleteNewsById(int newsId,String userId) throws NewsNotFoundException {
        final Page<PopularWatchItem> object = watchListRepo.fetchByIdAndUserId(newsId,userId);
        if (object == null) {
            throw new NewsNotFoundException("News not found");
        }
        for(PopularWatchItem watchListItem :object){
            watchListRepo.delete(watchListItem);
        }
        return true;
    }

    @Override
    public Page<Article> getAllNews(String userId) {
        return  (Page<Article>) watchListRepo.getAllWatchListNews(userId);
    }

    @Override
    public Page<Article> searchByTitle(String userId, String searchText) {
        return (Page<Article>) watchListRepo.searchNews(userId,searchText);
    }

}
