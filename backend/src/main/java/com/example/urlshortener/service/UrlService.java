package com.example.urlshortener.service;

import com.example.urlshortener.model.UrlEntity;
import com.example.urlshortener.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UrlService {

    @Autowired
    private UrlRepository urlRepository;

    // Shortens the given URL and stores it in the database
    public String shortenUrl(String originalUrl) {
        String shortUrl = UUID.randomUUID().toString().substring(0, 6); // Generate a random short code
        UrlEntity urlEntity = new UrlEntity(shortUrl, originalUrl);
        urlRepository.save(urlEntity);
        return shortUrl;
    }

    // Fetches the original URL from the shortened URL
    public String getOriginalUrl(String shortUrl) {
        UrlEntity urlEntity = urlRepository.findByShortUrl(shortUrl);
        return (urlEntity != null) ? urlEntity.getOriginalUrl() : null;
    }
}

