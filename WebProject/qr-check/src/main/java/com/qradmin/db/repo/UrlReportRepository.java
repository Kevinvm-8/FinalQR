package com.qradmin.db.repo;

import com.qradmin.db.UrlReport;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


public interface UrlReportRepository extends MongoRepository<UrlReport,String> {


}
