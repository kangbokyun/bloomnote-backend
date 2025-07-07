dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")

    implementation("org.springframework.boot:spring-boot-starter-security")

    implementation("org.mariadb.jdbc:mariadb-java-client")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

//    implementation(project(":domains:jwt-domain"))
//    implementation(project(":domains:core-domain"))
//    implementation(project(":domains:user-domain"))
//    implementation(project(":domains:social-domain"))

    implementation(project(":modules:bloomnote-jwt-module"))
    implementation(project(":modules:bloomnote-core-module"))
    implementation(project(":modules:bloomnote-database-module"))
    implementation(project(":modules:bloomnote-discovery-module"))
}

tasks {
    bootJar { enabled = true }
    jar { enabled = false }
}