package com.example.urlshortener.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "urls")
public class Url {
    @Id
    private String id;
    private String originalUrl;
    private String shortUrl;

    // Constructors, getters, setters
}
