dependencies {
    implementation("com.fasterxml.jackson.core:jackson-databind")
    implementation("org.springframework.boot:spring-boot-starter-data-redis")
}

tasks {
    bootJar { enabled = false }
    jar { enabled = true }
}