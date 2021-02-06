package com.mycompany.myapp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name="articles")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class NewsSourceResponse {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")


    @Column(name = "NEWS_SOURCERESPOSE_UUID", updatable = false, nullable = false)
    private UUID newsSourceResponseUUID;

    @OneToMany
    @JoinColumn(name = "SOURCE_UUID")
    private Source sourceUUID;

    @Column(name = "STATUS")
    private String status;

    public UUID getNewsSourceResponseUUID() {
        return newsSourceResponseUUID;
    }

    public void setNewsSourceResponseUUID(UUID newsSourceResponseUUID) {
        this.newsSourceResponseUUID = newsSourceResponseUUID;
    }

    public Source getSourceUUID() {
        return sourceUUID;
    }

    public void setSourceUUID(Source sourceUUID) {
        this.sourceUUID = sourceUUID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


}
