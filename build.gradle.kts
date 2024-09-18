import com.linecorp.support.project.multi.recipe.configureByLabels

plugins {
    id("java")
    id("io.spring.dependency-management") version Versions.springDependencyManagementPlugin apply false
    id("org.springframework.boot") version Versions.springBoot apply false
    id("io.freefair.lombok") version Versions.lombokPlugin apply false
    id("com.coditory.integration-test") version Versions.integrationTestPlugin apply false
    id("com.epages.restdocs-api-spec") version Versions.restdocsApiSpec apply false
    id("org.asciidoctor.jvm.convert") version Versions.asciidoctorPlugin apply false
    id("com.linecorp.build-recipe-plugin") version Versions.lineRecipePlugin
}

allprojects {
    group = "fast.campus"
    version = "1.0-SNAPSHOT"

    repositories {
        mavenCentral()
        maven { url = uri("https://maven.restlet.com") }
        maven { url = uri("https://jitpack.io") }
    }
}

subprojects {
    apply(plugin = "idea")
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

configureByLabels("java") {
    apply(plugin = "org.gradle.java")
    apply(plugin = "io.spring.dependency-management")
    apply(plugin = "io.freefair.lombok")
    apply(plugin = "com.coditory.integration-test")

    configure<JavaPluginExtension> {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }

    the<io.spring.gradle.dependencymanagement.dsl.DependencyManagementExtension>().apply {
        imports {
            mavenBom("org.springframework.boot:spring-boot-dependencies:${Versions.springBoot}")
            mavenBom("com.google.guava:guava-bom:${Versions.guava}")
        }

        dependencies {
            dependency("org.apache.commons:commons-lang3:${Versions.apacheCommonsLang}")
            dependency("org.apache.commons:commons-collections4:${Versions.apacheCommonsCollections}")
            dependency("com.navercorp.fixturemonkey:fixture-monkey-starter:${Versions.fixtureMonkey}")
            dependency("org.mapstruct:mapstruct:${Versions.mapstruct}")
            dependency("org.mapstruct:mapstruct-processor:${Versions.mapstruct}")
            dependency("com.fasterxml.jackson.core:jackson-databind:${Versions.jacksonCore}")

            dependency("org.junit.jupiter:junit-jupiter-api:${Versions.junit}")
            dependency("org.junit.jupiter:junit-jupiter-params:${Versions.junit}")
            dependency("org.junit.jupiter:junit-jupiter-engine:${Versions.junit}")
            dependency("org.assertj:assertj-core:${Versions.assertjCore}")
            dependency("org.mockito:mockito-junit-jupiter:${Versions.mockitoCore}")

            dependency("com.epages:restdocs-api-spec:${Versions.restdocsApiSpec}")
            dependency("com.epages:restdocs-api-spec-mockmvc:${Versions.restdocsApiSpec}")
            dependency("com.epages:restdocs-api-spec-restassured:${Versions.restdocsApiSpec}")

            dependencySet("io.jsonwebtoken:${Versions.jwt}") {
                entry("jjwt-api")
                entry("jjwt-impl")
                entry("jjwt-jackson")
            }
        }
    }

    dependencies {
        val implementation by configurations
        val annotationProcessor by configurations

        val testImplementation by configurations
        val testRuntimeOnly by configurations

        val integrationImplementation by configurations
        val integrationRuntimeOnly by configurations

        implementation("com.google.guava:guava")

        implementation("org.apache.commons:commons-lang3")
        implementation("org.apache.commons:commons-collections4")
        implementation("org.mapstruct:mapstruct")

        annotationProcessor("org.mapstruct:mapstruct-processor")

        testImplementation("org.junit.jupiter:junit-jupiter-api")
        testImplementation("org.assertj:assertj-core")
        testImplementation("org.junit.jupiter:junit-jupiter-params")
        testImplementation("org.mockito:mockito-core")
        testImplementation("org.mockito:mockito-junit-jupiter")
        testImplementation("com.navercorp.fixturemonkey:fixture-monkey-starter")
        testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")

        integrationImplementation("org.junit.jupiter:junit-jupiter-api")
        integrationImplementation("org.junit.jupiter:junit-jupiter-params")
        integrationImplementation("org.assertj:assertj-core")
        integrationRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
    }
}

configureByLabels("boot") {
    apply(plugin = "org.springframework.boot")

    tasks.getByName<Jar>("jar") {
        enabled = false
    }

    tasks.getByName<org.springframework.boot.gradle.tasks.bundling.BootJar>("bootJar") {
        enabled = true
        archiveClassifier.set("boot")
    }
}

configureByLabels("library") {
    apply(plugin = "java-library")

    tasks.getByName<Jar>("jar") {
        enabled = true
    }
}

configureByLabels("asciidoctor") {
    apply(plugin = "org.asciidoctor.jvm.convert")

    tasks.named<org.asciidoctor.gradle.jvm.AsciidoctorTask>("asciidoctor") {
        sourceDir(file("src/docs"))
        outputs.dir(file("build/docs"))
        attributes(
            mapOf(
                "snippets" to file("build/generated-snippets")
            )
        )
    }
}

configureByLabels("restdocs") {
    apply(plugin = "com.epages.restdocs-api-spec")
}

configureByLabels("querydsl") {
    the<io.spring.gradle.dependencymanagement.dsl.DependencyManagementExtension>().apply {
        imports {
            mavenBom("com.querydsl:querydsl-bom:${Versions.querydsl}")
        }

        dependencies {
            dependency("com.querydsl:querydsl-core:${Versions.querydsl}")
            dependency("com.querydsl:querydsl-jpa:${Versions.querydsl}")
            dependency("com.querydsl:querydsl-apt:${Versions.querydsl}")
        }
    }

    dependencies {
        val implementation by configurations
        val annotationProcessor by configurations

        implementation("com.querydsl:querydsl-jpa:${Versions.querydsl}:jakarta")
        implementation("com.querydsl:querydsl-core:${Versions.querydsl}")

        annotationProcessor("com.querydsl:querydsl-apt:${Versions.querydsl}:jakarta")
        annotationProcessor("jakarta.persistence:jakarta.persistence-api")
        annotationProcessor("jakarta.annotation:jakarta.annotation-api")
    }
}