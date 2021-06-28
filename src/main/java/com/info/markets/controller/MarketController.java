package com.info.markets.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.info.markets.model.MarketConfigurationEntity;
import com.info.markets.model.MarketResponse;
import com.info.markets.model.MarketResponseApiData;
import com.info.markets.sevice.MarketConfigurationServiceImpl;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/market")
public class MarketController {
    private final MarketConfigurationServiceImpl marketConfigurationServiceImpl;
    @Value("${market.api.access-key}")
    private String marketApiAccessKey;
    private static final Logger LOGGER = LoggerFactory.getLogger(MarketConfigurationServiceImpl.class);
    private ObjectMapper mapper = new ObjectMapper();


    @Autowired
    public MarketController(MarketConfigurationServiceImpl marketConfigurationServiceImpl){
        this.marketConfigurationServiceImpl = marketConfigurationServiceImpl;
    }

    @GetMapping("/stock-index")
    @ResponseStatus(HttpStatus.OK)
    public List<MarketResponseApiData> retrieveStockIndex(@RequestParam String stock) throws URISyntaxException, IOException, Exception {
        final MarketConfigurationEntity marketConfig = this.marketConfigurationServiceImpl.findMarketByStockIndex(stock);
        LOGGER.info(String.format("Market configuration, ", marketConfig));
        final HttpGet apiMarket = new HttpGet("http://api.marketstack.com/v1/eod");
        final CloseableHttpClient client = HttpClients.createDefault();
        URI uri = new URIBuilder(apiMarket.getURI())
                .addParameter("access_key", this.marketApiAccessKey)
                .addParameter("symbols", marketConfig.getMic())
                .build();
        apiMarket.setURI(uri);
        CloseableHttpResponse httpResponse = client.execute(apiMarket);
        if(httpResponse.getStatusLine().getStatusCode() != 200){
            throw new Exception(String.format("Bad request send to marketStack API", httpResponse));
        }
        LOGGER.info("marketStack API retrieve reach data");
        String response = EntityUtils.toString(httpResponse.getEntity());
        MarketResponse marketResponse = mapper.readValue(response, MarketResponse.class);
        client.close();
        return marketResponse.getData();
    }
}
