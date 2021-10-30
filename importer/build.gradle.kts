import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    idea
    application
    kotlin("jvm") version "1.5.31"
}

repositories {
    mavenCentral()
}

dependencies {
    // Include common project
    implementation(project(":common"))

    // Test framework
    testImplementation("io.kotest:kotest-runner-junit5:4.2.2")
    testImplementation("io.kotest:kotest-assertions-core:4.2.2")

    // Mocking
    testImplementation("io.mockk:mockk:1.10.0")
}

application {
    mainClass.set("uk.co.baconi.playground.db.importer.ApplicationKt")
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
