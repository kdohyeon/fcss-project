dependencies {
    implementation(project(":netplix-core:core-usecase"))
    implementation(project(":netplix-commons"))

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-aop")
    implementation("org.springframework.boot:spring-boot-starter-validation") // valid
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf") // valid
    implementation("org.thymeleaf.extras:thymeleaf-extras-springsecurity6")
    implementation("org.springframework.data:spring-data-commons") // Pageable
    implementation("org.springframework.boot:spring-boot-starter-actuator") // metric
    implementation("org.flywaydb:flyway-core")

    runtimeOnly(project(":netplix-adapters:adapter-http")) // to get adapter-http-property.yml
    runtimeOnly(project(":netplix-adapters:adapter-persistence")) // to get adapter-persistence-property.yml

    integrationImplementation("org.springframework.boot:spring-boot-starter-test")
    integrationImplementation("org.springframework.restdocs:spring-restdocs-mockmvc")
    integrationImplementation("io.rest-assured:spring-mock-mvc")
    integrationRuntimeOnly("com.h2database:h2")

    integrationImplementation("com.epages:restdocs-api-spec-mockmvc")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

val appMainClassName = "fast.campus.netplix.NetplixApiApplication"
tasks.getByName<org.springframework.boot.gradle.tasks.bundling.BootJar>("bootJar") {
    mainClass.set(appMainClassName)
    archiveClassifier.set("boot")
}
