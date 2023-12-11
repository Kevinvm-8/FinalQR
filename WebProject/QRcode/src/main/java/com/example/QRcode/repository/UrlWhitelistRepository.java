package com.example.QRcode.repository;

import com.example.QRcode.entity.UrlWhitelist;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UrlWhitelistRepository extends MongoRepository<UrlWhitelist,String> {
    Optional<UrlWhitelist> findByUrlW(String urlW);
}
