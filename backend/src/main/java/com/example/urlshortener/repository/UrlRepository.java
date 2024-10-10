package com.example.urlshortener.repository;

import com.example.urlshortener.model.UrlEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UrlRepository extends MongoRepository<UrlEntity, String> {
    UrlEntity findByShortUrl(String shortUrl);
}

