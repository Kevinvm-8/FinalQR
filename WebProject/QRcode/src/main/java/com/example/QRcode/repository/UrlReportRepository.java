package com.example.QRcode.repository;


import com.example.QRcode.entity.UrlBlacklist;
import com.example.QRcode.entity.UrlReport;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UrlReportRepository extends MongoRepository<UrlReport,String> {

    Optional<UrlReport> findByUrlReport(String urlReport);
}
