package com.qradmin.db.repo;

import com.qradmin.db.UrlList;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UrlListRepository extends MongoRepository<UrlList,String> {

    List<UrlList> findByUrl(String url);

    List<UrlList> findByIp(String ip);

    List<UrlList> findByIsBlackList(boolean b);
}
