load("@rules_proto_grpc//java:defs.bzl", "java_grpc_library")

java_binary(
    name = "candle_server",
    srcs = ["CandleServer.java"],
    main_class = "com.verlumen.tradestar.candles.CandleServer",
    deps = [
        "@grpc_server_runner//src/main/java/com/github/pselamy/grpc:grpc_server_runner",
        ":candle_server_module",
    ],
)

java_library(
    name = "candle_server_module",
    srcs = ["CandleServerModule.java"],
    deps = [
        ":candle_service_impl",
        "@maven//:com_google_inject_guice",
        "@tradestar_core//src/main/java/com/verlumen/tradestar/core/tradehistory/indicators:indicators_module",
        "@tradestar_grpc//:candle_service_java_grpc",
        "@tradestar_repos//src/main/java/com/verlumen/tradestar/repositories/candles:candle_repository_module",
    ],
)

java_library(
    name = "candle_service_impl",
    srcs = ["CandleServiceImpl.java"],
    deps = [
        "@maven//:com_google_inject_guice",
        "@maven//:org_ta4j_ta4j_core",
        "@tradestar_core//src/main/java/com/verlumen/tradestar/core/tradehistory:bar_series_factory",
        "@tradestar_core//src/main/java/com/verlumen/tradestar/core/tradehistory/indicators:indicator_adapter",
        "@tradestar_core//src/main/java/com/verlumen/tradestar/core/tradehistory/indicators:indicator_adapter_repository",
        "@tradestar_grpc//:candle_service_java_grpc",
        "@tradestar_protos//:candles_java_proto",
        "@tradestar_protos//:indicators_java_proto",
        "@tradestar_protos//:instruments_java_proto",
        "@tradestar_protos//:time_java_proto",
        "@tradestar_repos//src/main/java/com/verlumen/tradestar/repositories/candles:candle_repository",
    ],
)
