import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    idea
    `java-library`
    kotlin("jvm") version "1.5.31"
}

repositories {
    mavenCentral()
}

dependencies {
    // Logging
    implementation("ch.qos.logback:logback-classic:1.2.6")

    // Database Framework
    implementation("org.jetbrains.exposed:exposed-core:0.36.1")
    implementation("org.jetbrains.exposed:exposed-dao:0.36.1")
    implementation("org.jetbrains.exposed:exposed-jdbc:0.36.1")
    implementation("org.jetbrains.exposed:exposed-java-time:0.36.1")

    // Database Driver
    implementation("com.h2database:h2:1.4.200")

    // Test framework
    testImplementation("io.kotest:kotest-runner-junit5:4.2.2")
    testImplementation("io.kotest:kotest-assertions-core:4.2.2")

    // Mocking
    testImplementation("io.mockk:mockk:1.10.0")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = "11"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
        exceptionFormat = TestExceptionFormat.FULL
    }
}
