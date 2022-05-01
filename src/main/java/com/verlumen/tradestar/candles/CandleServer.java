package com.verlumen.tradestar.candles;

import com.github.pselamy.grpc.GrpcServerRunner;

public class CandleServer {
    /**
     * Main launches the server from the command line.
     */
    public static void main(String[] args) throws InterruptedException {
        GrpcServerRunner.run(new CandleServerModule());
    }
}
