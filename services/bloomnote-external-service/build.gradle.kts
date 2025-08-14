dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")

    implementation("org.mariadb.jdbc:mariadb-java-client")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    implementation(project(":modules:bloomnote-jpa-module"))
    implementation(project(":modules:bloomnote-core-module"))
    implementation(project(":modules:bloomnote-redis-module"))
    implementation(project(":modules:bloomnote-database-module"))
}

tasks {
    bootJar { enabled = true }
    jar { enabled = false }
}