workspace(name = "tradestar_services")

load("@bazel_tools//tools/build_defs/repo:git.bzl", "git_repository")

git_repository(
    name = "grpc_server_runner",
    commit = "94c56df2c7934ae8e01a9e717fbbd16eb44d0f75",
    remote = "https://github.com/pselamy/grpc-server-runner",
    shallow_since = "1650242961 -0500",
)

git_repository(
    name = "rules_proto",
    commit = "3212323502e21b819ac4fbdd455cb227ad0f6394",
    remote = "https://github.com/bazelbuild/rules_proto",
    shallow_since = "1649153521 +0200",
)

load("@rules_proto//proto:repositories.bzl", "rules_proto_dependencies", "rules_proto_toolchains")

rules_proto_dependencies()

rules_proto_toolchains()

git_repository(
    name = "tradestar_core",
    commit = "5c7b52d4ef7de728f24838a809821c877b031f7f",
    remote = "https://github.com/pselamy/tradestar-core",
    shallow_since = "1651366652 -0500",
)

git_repository(
    name = "tradestar_grpc",
    commit = "26fe735cc3fe81917cf456159a32d376ed5bbbb9",
    remote = "https://github.com/pselamy/tradestar-grpc",
    shallow_since = "1651341644 -0500",
)

git_repository(
    name = "tradestar_protos",
    commit = "13124d7710e9830767aa9931dcd2917f0349209a",
    remote = "https://github.com/pselamy/tradestar-protos",
    shallow_since = "1651376416 -0500",
)

git_repository(
    name = "tradestar_repos",
    commit = "a3c6badaafb84bf54eb71b43d96152b0e63ddf8c",
    remote = "https://github.com/pselamy/tradestar-repos",
    shallow_since = "1649619304 -0500",
)

git_repository(
    name = "contrib_rules_jvm",
    commit = "f7c08ec6d73ef691b03f843e0c2c3dbe766df584",
    remote = "https://github.com/bazel-contrib/rules_jvm",
    shallow_since = "1642674503 +0000",
)

load("@contrib_rules_jvm//:repositories.bzl", "contrib_rules_jvm_deps")

contrib_rules_jvm_deps()

load("@contrib_rules_jvm//:setup.bzl", "contrib_rules_jvm_setup")

contrib_rules_jvm_setup()

git_repository(
    name = "rules_proto_grpc",
    commit = "c618f7f7c06e130b3daa4c776ce4ba2401b260a2",
    remote = "https://github.com/rules-proto-grpc/rules_proto_grpc",
    shallow_since = "1640903766 +0000",
)

load("@rules_proto_grpc//:repositories.bzl", "rules_proto_grpc_repos", "rules_proto_grpc_toolchains")

rules_proto_grpc_toolchains()

rules_proto_grpc_repos()

load("@rules_proto_grpc//java:repositories.bzl", rules_proto_grpc_java_repos = "java_repos")

rules_proto_grpc_java_repos()

load("@io_grpc_grpc_java//:repositories.bzl", "IO_GRPC_GRPC_JAVA_ARTIFACTS", "IO_GRPC_GRPC_JAVA_OVERRIDE_TARGETS", "grpc_java_repositories")

grpc_java_repositories()

git_repository(
    name = "rules_jvm_external",
    remote = "https://github.com/bazelbuild/rules_jvm_external",
    tag = "4.2",
)

load("@rules_jvm_external//:defs.bzl", "maven_install")

maven_install(
    artifacts = [
        "com.google.guava:guava:31.1-jre",
        "com.google.inject:guice:5.1.0",
        "io.grpc:grpc-api:1.45.1",
        "org.apache.commons:commons-lang3:3.12.0",
        "org.ta4j:ta4j-core:0.14",
    ] + IO_GRPC_GRPC_JAVA_ARTIFACTS,
    generate_compat_repositories = True,
    override_targets = IO_GRPC_GRPC_JAVA_OVERRIDE_TARGETS,
    repositories = [
        "https://repo1.maven.org/maven2",
    ],
)

load("@maven//:compat.bzl", "compat_repositories")

compat_repositories()
