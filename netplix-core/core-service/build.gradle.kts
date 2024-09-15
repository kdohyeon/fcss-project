dependencies {
    implementation(project(":netplix-core:core-usecase"))
    implementation(project(":netplix-core:core-port"))
    implementation(project(":netplix-core:core-domain"))
    implementation(project(":netplix-commons"))

    implementation("org.springframework.data:spring-data-commons")
    implementation("org.springframework:spring-context")

    implementation("io.jsonwebtoken:jjwt-api")
    implementation("io.jsonwebtoken:jjwt-impl")
    implementation("io.jsonwebtoken:jjwt-jackson")
}
