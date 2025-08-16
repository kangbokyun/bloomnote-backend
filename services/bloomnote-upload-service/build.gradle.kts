dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")

    implementation("org.springframework.boot:spring-boot-starter-security")

    implementation("io.minio:minio:8.5.7")

    implementation("org.mariadb.jdbc:mariadb-java-client")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    implementation("org.springframework.cloud:spring-cloud-starter-openfeign")

    implementation(project(":domains:bloomnote-feign-domain"))

    implementation(project(":modules:bloomnote-jwt-module"))
    implementation(project(":modules:bloomnote-jpa-module"))
    implementation(project(":modules:bloomnote-core-module"))
    implementation(project(":modules:bloomnote-redis-module"))
    implementation(project(":modules:bloomnote-database-module"))
    implementation(project(":modules:bloomnote-discovery-module"))
}

tasks {
    bootJar { enabled = true }
    jar { enabled = false }
}