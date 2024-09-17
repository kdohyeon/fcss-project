dependencies {
    implementation(project(":netplix-core:core-domain"))
    implementation("io.jsonwebtoken:jjwt-api")
    implementation("io.jsonwebtoken:jjwt-impl")
    implementation("io.jsonwebtoken:jjwt-jackson")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}
