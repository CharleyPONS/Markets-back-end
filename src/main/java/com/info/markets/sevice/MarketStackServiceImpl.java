package com.info.markets.sevice;

import com.info.markets.core.mapper.UtilsMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

@Service
public class MarketStackServiceImpl implements MarketStackService{
    private static final Logger LOGGER = LoggerFactory.getLogger(MarketConfigurationService.class);
    private final RestTemplate restTemplate;
    private final UtilsMapping utilsMapping;

    @Autowired
    public MarketStackServiceImpl(RestTemplate restTemplate, UtilsMapping utilsMapping) {
        this.restTemplate = restTemplate;
        this.utilsMapping = utilsMapping;
    }

    public <S> HttpEntity<S>  externalCallApi(@NotNull String url, Map<String, String> queryParam, List<String> pathSegment, @NotNull Class<S> sourceClass) throws Exception {
        LOGGER.info(String.format("Start external api call for url", url));
        HttpHeaders header = new HttpHeaders();
        header.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
        if (!pathSegment.isEmpty()) {
            pathSegment.forEach(builder::pathSegment);
        }
        if (!queryParam.isEmpty()) {
            queryParam.forEach(builder::queryParam);
        }
        HttpEntity<S> response = this.restTemplate.getForEntity(
                builder.build().encode().toUriString(),
                sourceClass
        );
        if (!response.hasBody()) {
            throw new Exception("Bad request send to marketStack API");
        }
        LOGGER.info("marketStack API retrieve reach data");

        return response;
    }
}
