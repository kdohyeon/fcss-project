dependencies {
    implementation(project(":netplix-core:core-port"))

    implementation("org.springframework.boot:spring-boot-starter-data-jpa") // jpa
    implementation("jakarta.annotation:jakarta.annotation-api") // annotations
    implementation("jakarta.persistence:jakarta.persistence-api") // annotations
    implementation("org.springframework:spring-tx")

    implementation("org.flywaydb:flyway-core")
    implementation("org.flywaydb:flyway-mysql")

    implementation("com.querydsl:querydsl-jpa")
    implementation("com.querydsl:querydsl-core")

    runtimeOnly("com.mysql:mysql-connector-j")
    runtimeOnly(project(":netplix-core:core-service"))

    integrationImplementation("org.springframework.boot:spring-boot-starter-test")
}
