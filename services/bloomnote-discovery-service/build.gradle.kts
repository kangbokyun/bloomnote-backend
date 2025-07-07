dependencies {
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-server")
}

tasks {
    bootJar {
        archiveFileName="bloomnote-discovery-service.jar"
        enabled = true
    }
    jar { enabled = false }
}