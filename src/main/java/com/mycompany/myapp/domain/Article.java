package com.mycompany.myapp.domain;

import java.util.Date;
import java.util.UUID;

import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.hibernate.annotations.Cache;
import javax.persistence.*;
import javax.validation.constraints.Size;


@Entity
@Table(name="articles")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Article {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")


    @Column(name = "ARTICLE_UUID", updatable = false,nullable = false)
    private UUID articleUUID;

    @Column(name = "ARTICLE_WORDCOUNT")
    private Long articleWordCount;


    @Size(max = 1000)
    @Column(name = "HEADLINE",updatable = true, nullable = false)
    private String headline;

    @Column(name  = "CATEGORY")
    private String category;

    @OneToMany
    @JoinColumn(name = "SOURCE_UUID", nullable = false)
    private Source sourceUUID;


    @Column(name = "AUTHER", nullable = false)
    private String auther;

    @Column(name = "TITLE", nullable = false)
    private String  title;

    @Column(name = "DESCRIPTION", nullable = false)
    private String description;

    @Column(name = "URL")
    private String url;

    @Column(name = "URL_TO_IMAGE")
    private String url_To_Image;

    @Column(name = "PUBLISHED_AT")
    private String published_AT;


    public Article() {
    }

    public Article(@Size(max = 1000) String headline, Source sourceUUID, String auther, String title, String description, String url, String url_To_Image, String published_AT) {
        this.headline = headline;
        this.sourceUUID = sourceUUID;
        this.auther = auther;
        this.title = title;
        this.description = description;
        this.url = url;
        this.url_To_Image = url_To_Image;
        this.published_AT = published_AT;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "PUBDATE", updatable = false)
    @CreatedDate
    private Date pubDate;

    public UUID getArticleUUID() {
        return articleUUID;
    }

    public void setArticleUUID(UUID articleUUID) {
        this.articleUUID = articleUUID;
    }

    public Long getArticleWordCount() {
        return articleWordCount;
    }

    public void setArticleWordCount(Long articleWordCount) {
        this.articleWordCount = articleWordCount;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Source getSourceUUID() {
        return sourceUUID;
    }

    public void setSourceUUID(Source sourceUUID) {
        this.sourceUUID = sourceUUID;
    }

    public String getAuther() {
        return auther;
    }

    public void setAuther(String auther) {
        this.auther = auther;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl_To_Image() {
        return url_To_Image;
    }

    public void setUrl_To_Image(String url_To_Image) {
        this.url_To_Image = url_To_Image;
    }

    public String getPublished_AT() {
        return published_AT;
    }

    public void setPublished_AT(String published_AT) {
        this.published_AT = published_AT;
    }

    public Date getPubDate() {
        return pubDate;
    }

    public void setPubDate(Date pubDate) {
        this.pubDate = pubDate;
    }

    @Override
    public String toString() {
        return "Article{" +
            "articleUUID=" + articleUUID +
            ", articleWordCount=" + articleWordCount +
            ", headline='" + headline + '\'' +
            ", category='" + category + '\'' +
            ", sourceUUID=" + sourceUUID +
            ", auther='" + auther + '\'' +
            ", title='" + title + '\'' +
            ", description='" + description + '\'' +
            ", url='" + url + '\'' +
            ", url_To_Image='" + url_To_Image + '\'' +
            ", published_AT='" + published_AT + '\'' +
            ", pubDate=" + pubDate +
            '}';
    }
}
