package com.info.markets.sevice;

import com.info.markets.model.MarketStack.ticker.TickerEOD;
import com.info.markets.model.MarketStack.ticker.TickerIntraday;
import org.springframework.http.HttpEntity;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

public interface MarketStackService {
    public List<TickerEOD> findAllTickerEODByMarket();

    public List<TickerIntraday> findAllTickerIntradayByMarket();

    public <S> HttpEntity<S> externalCallApi(@NotNull String url, Map<String, String> queryParam, List<String> pathSegment, @NotNull Class<S> sourceClass) throws Exception;
}
