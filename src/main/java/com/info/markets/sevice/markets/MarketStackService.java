package com.info.markets.sevice.markets;

import com.info.markets.model.MarketStack.ticker.TickerEOD;
import com.info.markets.model.MarketStack.ticker.TickerIntraday;
import org.springframework.http.HttpEntity;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public interface MarketStackService {


    public <S> HttpEntity<S> externalCallApi(@NotNull String url, Map<String, String> queryParam, List<String> pathSegment, @NotNull Class<S> sourceClass) throws Exception;

    public <S> CompletableFuture<HttpEntity<S>> findAllTickerByMarket(@NotNull String url, Map<String, String> queryParam, List<String> pathSegment, @NotNull Class<S> sourceClass) throws Exception;
}
