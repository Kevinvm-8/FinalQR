package com.qradmin.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
@Getter
public class ResourceProperties {

    @Value("${api.timeout}")
    private long timeout;

    @Value("${max.request.per.second}")
    private int maxRequestPerSecondByToken;
    @Value("${max.request.per.second.total}")
    private int maxRequestPerSecondByTotal;
    @Value("${time-unit.expired.cached}")
    private TimeUnit timeUnitExpiredCached;
    @Value("${duration.expired.cached}")
    private int durationExpiredCached;
    @Value("${filter.request.size.name}")
    private String keyReqSizeCacheName;
    @Value("${filter.total.request.size}")
    private int maxTotalRequestSizeSecond;
}
