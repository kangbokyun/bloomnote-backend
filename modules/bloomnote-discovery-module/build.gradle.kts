dependencies {
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client")
}

tasks {
    bootJar { enabled = false }
    jar { enabled = true }
}