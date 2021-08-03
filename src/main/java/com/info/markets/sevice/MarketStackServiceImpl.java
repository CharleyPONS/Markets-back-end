package com.info.markets.sevice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Service
public class MarketStackServiceImpl implements MarketStackService{
    private static final Logger LOGGER = LoggerFactory.getLogger(MarketConfigurationService.class);
    private final RestTemplate restTemplate;

    @Autowired
    public MarketStackServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Async("threadPoolTaskExecutor1")
    public <S>CompletableFuture<HttpEntity<S>> findAllTickerByMarket(@NotNull String url, Map<String, String> queryParam, List<String> pathSegment, @NotNull Class<S> sourceClass) throws Exception {
        LOGGER.info(String.format("Start working on separate thread", Thread.currentThread().getName()));
        return CompletableFuture.completedFuture(this.externalCallApi(url, queryParam, pathSegment, sourceClass));
    }

    public <S> HttpEntity<S>  externalCallApi(@NotNull String url, Map<String, String> queryParam, List<String> pathSegment, @NotNull Class<S> sourceClass) throws Exception {
        LOGGER.info(String.format("Start external api call for url", url));
        new HttpHeaders().set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
        if (!pathSegment.isEmpty()) {
            pathSegment.forEach(builder::pathSegment);
        }
        if (!queryParam.isEmpty()) {
            queryParam.forEach(builder::queryParam);
        }
        try {
            HttpEntity<S> response = this.restTemplate.getForEntity(
                    builder.build().encode().toUriString(),
                    sourceClass
            );
            if (!response.hasBody()) {
                throw new Exception("Bad request send to marketStack API");
            }
            LOGGER.info("marketStack API retrieve reach data");

            return response;
        } catch (Exception e){
            LOGGER.info(String.format(e.getMessage(), "for request", builder.toUriString()));
          throw new Exception("Request to market stack fail with error" + String.format(e.getMessage(), "for path and query" ,  queryParam, pathSegment));
        }
    }
}
