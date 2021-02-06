package com.mycompany.myapp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;


@Entity
@Table(name="articles")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Source {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")


    @Column(name = "SOURCE_UUID", updatable = false, nullable = false)
    private UUID sourceUUID;

    @Column(name = "SOURCE_NAME")
    private String sourceName;

    @Column(name = "CATEGORY")
    private String category;

    @Column(name = "LANGUAGE")
    private String language;

    @Column(name = "COUNTRY")
    private String country;

    public Source(String sourceName) {
        this.sourceName = sourceName;
    }

    public UUID getSourceUUID() {
        return sourceUUID;
    }

    public void setSourceUUID(UUID sourceUUID) {
        this.sourceUUID = sourceUUID;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
