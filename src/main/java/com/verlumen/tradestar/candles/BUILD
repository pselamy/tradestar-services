load("@rules_proto_grpc//java:defs.bzl", "java_grpc_library")

java_binary(
    name = "candle_server",
    srcs = ["CandleServer.java"],
    main_class = "com.verlumen.tradestar.candles.CandleServer",
    deps = [
        "@grpc_server_runner//src/main/java/com/github/pselamy/grpc:grpc_server_runner",
        "@maven//:com_google_inject_guice",
        "@tradestar_core//src/main/java/com/verlumen/tradestar/core/tradehistory:bar_series_factory",
        "@tradestar_grpc//:candle_service_java_grpc",
        "@tradestar_protos//:candles_java_proto",
        "@tradestar_protos//:instruments_java_proto",
        "@tradestar_protos//:time_java_proto",
        "@tradestar_repos//src/main/java/com/verlumen/tradestar/repositories/candles:candle_repository",
        "@tradestar_repos//src/main/java/com/verlumen/tradestar/repositories/candles:candle_repository_module",
    ],
)
