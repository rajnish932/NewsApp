package com.mycompany.myapp.web.rest;

import java.util.List;
import java.util.UUID;

import javax.servlet.ServletRequest;
    import javax.servlet.ServletResponse;
    import javax.servlet.http.HttpServletRequest;

import com.mycompany.myapp.domain.Article;
import com.mycompany.myapp.service.PopularWatchItemService;
import exception.NewsAlreadyExistsException;
import exception.NewsNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.stereotype.Controller;
    import org.springframework.web.bind.annotation.CrossOrigin;
    import org.springframework.web.bind.annotation.DeleteMapping;
    import org.springframework.web.bind.annotation.GetMapping;
    import org.springframework.web.bind.annotation.PathVariable;
    import org.springframework.web.bind.annotation.PostMapping;
    import org.springframework.web.bind.annotation.RequestBody;
    import org.springframework.web.bind.annotation.RequestMapping;

    import io.jsonwebtoken.Jwts;

/**
 * @author Adarsh
 * Controller used to expose services for watchlist
 * Used by user  watch list interaction
 */
@Controller
@RequestMapping("api/v1/watchlist")
@CrossOrigin(origins = "*")
public class PopularWatchItemResource {
    @Qualifier("watchListManagerService")
    @Autowired
    private PopularWatchItemService watchListManagerService;

    /**
     * API to add news to watchlist
     * @param news
     * @param request
     * @return
     */
    @PostMapping
    public ResponseEntity<?>addToWatchList(@RequestBody Article news, HttpServletRequest request) {
        ResponseEntity<?> responseEntity = null;
        try {
            final String authHeader = request.getHeader("authorization");
            final String token = authHeader.substring(7);
            String userId=Jwts.parser().setSigningKey("secretkey").parseClaimsJws(token).getBody().getSubject();
            news.setArticleUUID(UUID.fromString(userId));
            watchListManagerService.saveNews(news);
            responseEntity = new ResponseEntity<Article>(news, HttpStatus.CREATED);
        } catch (NewsAlreadyExistsException exception) {
            responseEntity = new ResponseEntity<String>("{ message :" + exception.getMessage() + "}",
                HttpStatus.CONFLICT);
        }
        return responseEntity;
    }
    /**
     * API to delete news by  id
     * @param id
     * @return responseEntity
     */
    @DeleteMapping(path = "/{newsId}")
    public ResponseEntity<?> deleteMovie(@PathVariable("newsId") int id,HttpServletRequest request) {
        ResponseEntity<?> responseEntity = null;
        try {
            final String authHeader = request.getHeader("authorization");
            final String token = authHeader.substring(7);
            String userId=Jwts.parser().setSigningKey("secretkey").parseClaimsJws(token).getBody().getSubject();

            boolean deleteStatus = watchListManagerService.deleteNewsById(id,userId);
            responseEntity = new ResponseEntity<Boolean>(deleteStatus, HttpStatus.OK);
        } catch (NewsNotFoundException exception) {
            responseEntity = new ResponseEntity<String>("{ message :" + exception.getMessage() + "}",
                HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

    /**
     * API to get all news to specific user
     * @param req
     * @param res
     * @return
     */
    @GetMapping
    public ResponseEntity<?> getAllNews(final HttpServletRequest request,final ServletResponse res) {
        ResponseEntity<?> responseEntity = null;
        final String authHeader = request.getHeader("authorization");
        final String token = authHeader.substring(7);
        String userId=Jwts.parser().setSigningKey("secretkey").parseClaimsJws(token).getBody().getSubject();

        Page<Article> news = watchListManagerService.getAllNews(userId);
        responseEntity = new ResponseEntity<Page<Article>>(news, HttpStatus.OK);
        return responseEntity;
    }

    @GetMapping("/search/{searchText}")
    public ResponseEntity<?> searchNews(final HttpServletRequest request,final ServletResponse res,@PathVariable("searchText") String searchText) {
        ResponseEntity<?> responseEntity = null;
        final String authHeader = request.getHeader("authorization");
        final String token = authHeader.substring(7);
        String userId=Jwts.parser().setSigningKey("secretkey").parseClaimsJws(token).getBody().getSubject();

        Page<Article> news = watchListManagerService.searchByTitle(userId, "%"+searchText.toUpperCase()+"%");
        responseEntity = new ResponseEntity<Page<Article>>(news, HttpStatus.OK);
        return responseEntity;
    }





    
}
