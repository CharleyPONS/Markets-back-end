package com.info.markets.controller;

import com.info.markets.core.mapper.UtilsMapping;
import com.info.markets.dto.MarketDTO;
import com.info.markets.model.configuration.MarketConfigurationEntity;
import com.info.markets.model.MarketStack.MarketResponse;
import com.info.markets.sevice.MarketConfigurationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/market")
public class MarketController {
    private final MarketConfigurationService marketConfigurationService;
    @Value("${market.api.access-key}")
    private String marketApiAccessKey;
    @Value("${market.api.uri}")
    private String marketApiUri;
    private static final Logger LOGGER = LoggerFactory.getLogger(MarketConfigurationService.class);
    private final RestTemplate restTemplate;
    private final UtilsMapping utilsMapping;


    @Autowired
    public MarketController(MarketConfigurationService marketConfigurationService, RestTemplate restTemplate, UtilsMapping utilsMapping){
        this.marketConfigurationService = marketConfigurationService;
        this.restTemplate = restTemplate;
        this.utilsMapping = utilsMapping;
    }

    @GetMapping("/stock-index")
    @ResponseStatus(HttpStatus.OK)
    public List<MarketDTO> retrieveStockIndex(@RequestParam("stock") String stock) throws URISyntaxException, IOException, Exception {
        final MarketConfigurationEntity marketConfig = this.marketConfigurationService.findMarketByStockIndex(stock);
        LOGGER.info(String.format("Market configuration, ", marketConfig));
        HttpHeaders header = new HttpHeaders();
        header.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(this.marketApiUri)
                .pathSegment("eod")
                .queryParam("access_key", this.marketApiAccessKey)
                .queryParam("symbols", marketConfig.getMic());
        HttpEntity<?> entity = new HttpEntity<>(header);
        HttpEntity<MarketResponse> response = this.restTemplate.getForEntity(
                builder.build().encode().toUriString(),
                MarketResponse.class
        );
        if(!response.hasBody()){
            throw new Exception("Bad request send to marketStack API");
        }
        System.out.println(response.getBody());
        LOGGER.info("marketStack API retrieve reach data");
        return this.utilsMapping.
                ConvertEntityToDTO(Objects.requireNonNull(response.getBody()).getData(),
                MarketDTO.class);
    }

}
