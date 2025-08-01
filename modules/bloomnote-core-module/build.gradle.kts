dependencies {
    implementation("io.jsonwebtoken:jjwt-api:0.12.6")
    runtimeOnly("io.jsonwebtoken:jjwt-impl:0.12.6")
    runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.12.6")
}

tasks {
    bootJar { enabled = false }
    jar {
        enabled = true
        archiveFileName.set("bloomnote-core-module.jar")
    }
}