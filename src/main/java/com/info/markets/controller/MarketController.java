package com.info.markets.controller;

import com.info.markets.core.mapper.UtilsMapping;
import com.info.markets.dto.TickerDTO;
import com.info.markets.dto.TickerEndOfDayDTO;
import com.info.markets.dto.TickerIntradayDTO;
import com.info.markets.dto.VolumeMarketTickerDataDTO;
import com.info.markets.model.MarketStack.ticker.TickerEOD;
import com.info.markets.model.MarketStack.ticker.TickerInformationResponse;
import com.info.markets.model.MarketStack.ticker.TickerIntraday;
import com.info.markets.model.configuration.MarketConfigurationEntity;
import com.info.markets.model.MarketStack.market.MarketResponse;
import com.info.markets.model.configuration.TickersConfigurationEntity;
import com.info.markets.sevice.markets.MarketConfigurationService;
import com.info.markets.sevice.markets.MarketStackService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/market")
public class MarketController {
    private final MarketConfigurationService marketConfigurationService;
    @Value("${market.api.access-key}")
    private String marketApiAccessKey;
    @Value("${market.api.uri}")
    private String marketApiUri;
    private static final Logger LOGGER = LoggerFactory.getLogger(MarketConfigurationService.class);
    private final UtilsMapping utilsMapping;
    private final MarketStackService marketStackService;


    @Autowired
    public MarketController(MarketConfigurationService marketConfigurationService, UtilsMapping utilsMapping, MarketStackService marketStackService) {
        this.marketConfigurationService = marketConfigurationService;
        this.utilsMapping = utilsMapping;
        this.marketStackService = marketStackService;
    }

    @GetMapping("/stock-index")
    @ResponseStatus(HttpStatus.OK)
    public List<VolumeMarketTickerDataDTO> retrieveStockIndex(@RequestParam("stock") String stock) throws URISyntaxException, IOException, Exception {
        final MarketConfigurationEntity marketConfig = this.marketConfigurationService.findMarketByStockIndex(stock);
        LOGGER.info(String.format("Market configuration, ", marketConfig));
        List<String> path = new ArrayList<String>(
                List.of("eod")
        );
        Map<String, String> query = Map.of("access_key", this.marketApiAccessKey, "symbols", marketConfig.getMic());
        HttpEntity<MarketResponse> response = this.marketStackService.externalCallApi(this.marketApiUri, query, path, MarketResponse.class);
        return this.utilsMapping.
                convertListEntityToDTO(Objects.requireNonNull(response.getBody()).getData(),
                        VolumeMarketTickerDataDTO.class);
    }

    @GetMapping("/ticker-stock-index")
    @ResponseStatus(HttpStatus.OK)
    public List<TickerDTO> retrieveAllTickerByStockIndex(@RequestParam("stock") String stock) throws Exception {
        final MarketConfigurationEntity marketConfig = this.marketConfigurationService.findMarketByStockIndex(stock);
        LOGGER.info(String.format("Market configuration, ", marketConfig));
        List<String> path = new ArrayList<String>(
                List.of("tickers")
        );
        Map<String, String> query = Map.of("access_key", this.marketApiAccessKey, "exchange", marketConfig.getMic());
        HttpEntity<TickerInformationResponse> response = this.marketStackService.externalCallApi(this.marketApiUri, query, path, TickerInformationResponse.class);
        return this.utilsMapping.convertListEntityToDTO(Objects.requireNonNull(response.getBody()).getData(), TickerDTO.class);
    }

    @GetMapping("/ticker-eod/{ticker}")
    @ResponseStatus(HttpStatus.OK)
    public TickerEndOfDayDTO retrieveTickerEndOfDayData(@PathVariable("ticker") String ticker) throws Exception {
        List<String> path = new ArrayList<String>(
                List.of("tickers", ticker, "eod")
        );
        Map<String, String> query = Map.of("access_key", this.marketApiAccessKey);
        HttpEntity<TickerEOD> response = this.marketStackService.externalCallApi(this.marketApiUri, query, path, TickerEOD.class);
        return this.utilsMapping.convertObjectEntityToDTO(Objects.requireNonNull(response.getBody()).getData(), TickerEndOfDayDTO.class);
    }

    @GetMapping("/ticker-intraday/{ticker}")
    @ResponseStatus(HttpStatus.OK)
    public TickerIntradayDTO retrieveTickerIntraDayData(@PathVariable("ticker") String ticker) throws Exception {
        List<String> path = new ArrayList<String>(
                List.of("tickers", ticker, "intraday")
        );
        Map<String, String> query = Map.of("access_key", this.marketApiAccessKey);
        HttpEntity<TickerIntraday> response = this.marketStackService.externalCallApi(this.marketApiUri, query, path, TickerIntraday.class);
        return this.utilsMapping.convertObjectEntityToDTO(Objects.requireNonNull(response.getBody()).getData(), TickerIntradayDTO.class);
    }

    @GetMapping("/all-ticker-eod/{market}")
    @ResponseStatus(HttpStatus.OK)
    public List<TickerEndOfDayDTO> retrieveAllTickerEndDayData(@PathVariable("market") String market) throws Exception {
        MarketConfigurationEntity marketConfiguration = this.marketConfigurationService.findMarketByStockIndex(market);
        if (marketConfiguration.getTickers().isEmpty()) {
            throw new Exception("No ticker provided for this market");
        }
        Map<String, String> query = Map.of("access_key", this.marketApiAccessKey);
        final List<CompletableFuture<HttpEntity<TickerEOD>>> response = new ArrayList<>();
        for (TickersConfigurationEntity v : marketConfiguration.getTickers().stream().filter(TickersConfigurationEntity::isHas_eod).collect(Collectors.toList())) {
            response.add(this.marketStackService.findAllTickerByMarket(this.marketApiUri, query, new ArrayList<>(
                    List.of("tickers", v.getSymbol().endsWith(".PA") ? v.getSymbol().replace(".PA", ".XPAR") : v.getSymbol().concat(".XPAR"), "eod")
            ), TickerEOD.class));
        }
        CompletableFuture.allOf(response.toArray(new CompletableFuture[response.size()]));
        return this.utilsMapping.convertListEntityToDTO(Objects.requireNonNull( response
                .stream()
                .map(CompletableFuture::join)
                .map(v -> v.getBody().getData())
                .collect(Collectors.toList())), TickerEndOfDayDTO.class);
    }

    @GetMapping("/test")
    @ResponseStatus(HttpStatus.OK)
    public void test() {
        List<MarketConfigurationEntity> ce = this.marketConfigurationService.findAllMarket();
    }
}
