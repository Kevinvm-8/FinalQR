package com.example.QRcode.repository;

import com.example.QRcode.entity.UrlBlacklist;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface UrlBlacklistRepository extends MongoRepository<UrlBlacklist,String> {

    Optional<UrlBlacklist> findByUrlB(String urlB);

}
