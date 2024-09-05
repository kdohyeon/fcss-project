dependencies {
    implementation(project(":netplix-core:core-usecase"))
    implementation(project(":netplix-commons"))

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-aop")
    implementation("org.springframework.boot:spring-boot-starter-validation") // valid
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf") // valid
    implementation("org.springframework.boot:spring-boot-starter-actuator") // metric
    implementation("org.springframework.data:spring-data-commons") // Pageable
    implementation("org.thymeleaf.extras:thymeleaf-extras-springsecurity6")

    implementation("io.jsonwebtoken:jjwt-api:${Versions.jwt}")
    implementation("io.jsonwebtoken:jjwt-impl:${Versions.jwt}")
    implementation("io.jsonwebtoken:jjwt-jackson:${Versions.jwt}")

    implementation("org.flywaydb:flyway-core")

    runtimeOnly(project(":netplix-adapters:adapter-http"))
    runtimeOnly(project(":netplix-adapters:adapter-persistence"))
    runtimeOnly(project(":netplix-adapters:adapter-redis"))

    integrationImplementation("org.springframework.boot:spring-boot-starter-test")
    integrationImplementation("org.springframework.restdocs:spring-restdocs-mockmvc")
    integrationImplementation("io.rest-assured:spring-mock-mvc")

    integrationImplementation("com.epages:restdocs-api-spec-mockmvc")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

val appMainClassName = "fast.campus.netplix.NetplixApiApplication"
tasks.getByName<org.springframework.boot.gradle.tasks.bundling.BootJar>("bootJar") {
    mainClass.set(appMainClassName)
    archiveClassifier.set("boot")
}
