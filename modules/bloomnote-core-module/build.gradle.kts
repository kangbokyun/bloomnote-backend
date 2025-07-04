plugins {
    kotlin("plugin.jpa") version "1.9.22"
}

dependencies {
    implementation("org.mariadb.jdbc:mariadb-java-client")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
}

tasks {
    bootJar { enabled = false }
    jar {
        enabled = true
        archiveFileName.set("bloomnote-core-module.jar")
    }
}