package com.example.urlshortener.controller;

import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import com.example.urlshortener.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/urls")
@CrossOrigin(origins = "http://localhost:5173")

public class UrlController {

    @Autowired
    private UrlService urlService;

    // Endpoint to shorten a URL
    @PostMapping("/shorten")
    public ResponseEntity<String> shortenUrl(@RequestBody String originalUrl) {
        String shortUrl = urlService.shortenUrl(originalUrl);
        return ResponseEntity.ok(shortUrl);
    }

    // Endpoint to handle redirection from shortened URL to original URL
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
