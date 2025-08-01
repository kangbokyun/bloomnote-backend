dependencies {
    implementation("org.springframework.cloud:spring-cloud-starter-gateway")
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client")

    developmentOnly("io.netty:netty-resolver-dns-native-macos:4.1.89.Final:osx-aarch_64")

    runtimeOnly("io.jsonwebtoken:jjwt-impl:0.12.6")
    implementation("io.jsonwebtoken:jjwt-api:0.12.6")
    runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.12.6")

    implementation(project(":modules:bloomnote-core-module"))
}

tasks {
    bootJar {
        archiveFileName="bloomnote-gateway-service.jar"
        enabled = true
    }
    jar { enabled = false }
}