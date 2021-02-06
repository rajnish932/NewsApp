package com.mycompany.myapp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.UUID;


@Entity
@Table(name = "PopularWatchItem")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@NamedQueries({
    @NamedQuery(name = "PopularWatchItem.fetchByIdAndUserId",
        query = "SELECT w FROM PopularWatchItem w WHERE w.news.newsId =:id and w.userId=:userId"
    ),
    @NamedQuery(name = "PopularWatchItem.getAllWatchListNews",
        query = "SELECT w.news FROM PopularWatchItem w WHERE w.userId=:userId"
    ),
    @NamedQuery(name = "PopularWatchItem.searchNews",
        query = "SELECT w.news FROM PopularWatchItem w WHERE w.userId=:userId and uppercase(w.news.title) like :searchparam"
    )
})

public class PopularWatchItem {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")

    @Column(name = "watch_list_id")
    private UUID watchListId;

    @OneToOne(cascade = CascadeType.MERGE)
    private Article news;

    public PopularWatchItem() {
        super();
    }

    @Column(name = "user_id")
    private String userId;


    public UUID getWatchListId() {
        return watchListId;
    }

    public void setWatchListId(UUID watchListId) {
        this.watchListId = watchListId;
    }

    public Article getNews() {
        return news;
    }

    public PopularWatchItem(Article news, String userId) {
        super();
        this.news = news;
        this.userId = userId;
    }

    public void setNews(Article news) {
        this.news = news;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
