package com.verlumen.tradestar.candles;

import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Range;
import com.google.inject.Inject;
import com.verlumen.tradestar.core.tradehistory.BarSeriesFactory;
import com.verlumen.tradestar.core.tradehistory.indicators.IndicatorAdapter;
import com.verlumen.tradestar.core.tradehistory.indicators.IndicatorAdapterRepository;
import com.verlumen.tradestar.protos.candles.*;
import com.verlumen.tradestar.protos.indicators.Indicator;
import com.verlumen.tradestar.protos.instruments.Instrument;
import com.verlumen.tradestar.protos.time.TimeInterval;
import com.verlumen.tradestar.repositories.candles.CandleRepository;
import io.grpc.stub.StreamObserver;
import org.ta4j.core.BarSeries;

import java.time.Instant;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.collect.ImmutableList.toImmutableList;
import static com.google.common.collect.ImmutableSet.toImmutableSet;
import static java.time.Instant.ofEpochSecond;
import static java.util.stream.IntStream.iterate;

class CandleServiceImpl extends CandleServiceGrpc.CandleServiceImplBase {
    private final CandleRepository candleRepository;
    private final IndicatorAdapterRepository indicatorAdapterRepository;

    @Inject
    CandleServiceImpl(CandleRepository candleRepository, IndicatorAdapterRepository indicatorAdapterRepository) {
        this.candleRepository = candleRepository;
        this.indicatorAdapterRepository = indicatorAdapterRepository;
    }

    private static Range<Instant> getTimeRange(TimeInterval timeInterval) {
        Instant start = ofEpochSecond(timeInterval.getStart().getSeconds());
        Instant end = ofEpochSecond(timeInterval.getEnd().getSeconds());
        return Range.closedOpen(start, end);
    }

    @Override
    public void getCandles(GetCandlesRequest req,
                           StreamObserver<GetCandlesResponse> responseObserver) {
        checkArgument(req.hasInstrument());
        checkArgument(!req.getGranularity().equals(Granularity.UNSPECIFIED));
        checkArgument(req.hasTimeInterval());
        ImmutableSet<Candle> candles = getCandles(
                getTimeRange(req.getTimeInterval()), req.getInstrument(),
                req.getGranularity());
        BarSeries series = BarSeriesFactory.create(
                req.getGranularity(), candles.asList());
        ImmutableSet<IndicatorAdapter> indicatorAdapters = req.getIndicatorParamsList()
                .stream()
                .map(params -> indicatorAdapterRepository.get(params, series))
                .collect(toImmutableSet());

        GetCandlesResponse reply = GetCandlesResponse.newBuilder()
                .addAllCandles(addIndicators(candles, indicatorAdapters))
                .build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }

    private ImmutableCollection<Candle> addIndicators(
            ImmutableCollection<Candle> candles,
            ImmutableCollection<IndicatorAdapter> indicatorAdapters) {
        if (indicatorAdapters.isEmpty()) {
            return candles;
        }

        ImmutableList<Candle> candleList = candles.asList();
        return iterate(0, i -> i + 1)
                .limit(candleList.size())
                .mapToObj(i -> candleList.get(i).toBuilder()
                        .addAllIndicators(indicators(indicatorAdapters, i))
                        .build())
                .collect(toImmutableSet());
    }

    private ImmutableList<Indicator> indicators(ImmutableCollection<IndicatorAdapter> indicatorAdapters, int i) {
        return indicatorAdapters.stream()
                .map(indicatorAdapter -> indicatorAdapter.indicator(i))
                .collect(toImmutableList());
    }

    private ImmutableSet<Candle> getCandles(Range<Instant> timeRange, Instrument instrument, Granularity granularity) {
        return candleRepository.getCandles(instrument, granularity,
                timeRange);
    }
}
