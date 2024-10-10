package com.example.urlshortener.controller;

import com.example.urlshortener.model.UrlEntity;
import com.example.urlshortener.repository.UrlRepository;
import com.example.urlshortener.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class UrlController {

    @Autowired
    private UrlRepository urlRepository;

    @Autowired
    private UrlService urlService;

    // POST Endpoint to shorten a URL
    @PostMapping("/api/urls/shorten")
    public ResponseEntity<String> shortenUrl(@RequestBody String originalUrl) {
        String shortUrl = urlService.shortenUrl(originalUrl);
        return ResponseEntity.ok(shortUrl);
    }

    // Redirect based on short URL (direct from root path, e.g., /c6649d)
    @GetMapping("/{shortUrl}")
    public void redirectUrl(HttpServletResponse response, @PathVariable String shortUrl) throws IOException {
        String originalUrl = urlService.getOriginalUrl(shortUrl);
        if (originalUrl != null) {
            response.sendRedirect(originalUrl);  // Redirect to the original URL
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "No URL found for: " + shortUrl);  // Handle non-existent short URL
        }
    }
}


