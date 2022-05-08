load(
    "@io_bazel_rules_docker//container:container.bzl",
    "container_push",
)

container_push(
    name = "push_latest_candle_service",
    format = "Docker",
    image = "//src/main/java/com/verlumen/tradestar/candles:candle_server",
    registry = "$(registry)",
    repository = "$(project)/candle-service",
    tag = "$(tag)",
)
