dependencies {
    implementation(project(":netplix-core:core-usecase"))
    implementation(project(":netplix-adapters:adapter-persistence"))
    implementation(project(":netplix-commons"))

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-batch")
    implementation("com.h2database:h2")

//    runtimeOnly(project(":netplix-adapters:adapter-persistence"))
    runtimeOnly(project(":netplix-core:core-service"))
}

val appMainClassName = "fast.campus.netplix.NetplixBatchApplication"
tasks.getByName<org.springframework.boot.gradle.tasks.bundling.BootJar>("bootJar") {
    mainClass.set(appMainClassName)
    archiveClassifier.set("boot")
}
