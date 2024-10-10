package com.example.urlshortener.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.urlshortener.model.UrlEntity;
import com.example.urlshortener.repository.UrlRepository;
import java.util.UUID;

@Service
public class UrlService {
    @Autowired
    private UrlRepository urlRepository;

    
public String shortenUrl(String originalUrl) {
    String shortUrl = UUID.randomUUID().toString().substring(0, 6); // Generate a short URL
    // Clean original URL by trimming whitespace and removing quotes
    originalUrl = originalUrl.trim().replaceAll("^\"|\"$", ""); 
    UrlEntity urlEntity = new UrlEntity(shortUrl, originalUrl); // Create UrlEntity
    urlRepository.save(urlEntity);
    return shortUrl;
}


    public String getOriginalUrl(String shortUrl) {
        UrlEntity urlEntity = urlRepository.findByShortUrl(shortUrl);
        return urlEntity != null ? urlEntity.getOriginalUrl() : null;
    }
}



