package com.verlumen.tradestar.candles;

import com.google.inject.AbstractModule;
import com.google.inject.multibindings.Multibinder;
import com.verlumen.tradestar.repositories.candles.CandleRepositoryModule;
import io.grpc.BindableService;

class CandleServerModule extends AbstractModule {
    @Override
    protected void configure() {
        Multibinder<BindableService> serviceBinder =
                Multibinder.newSetBinder(binder(), BindableService.class);
        serviceBinder.addBinding().to(CandleServiceImpl.class);

        install(new CandleRepositoryModule());
    }
}
