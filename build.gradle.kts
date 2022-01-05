plugins {
    id("maven")
    id("java-library")
    kotlin("jvm") version "1.6.10"
    kotlin("plugin.serialization") version "1.4.21"
}

group = "uno.d1s"
version = "0.2.1-beta.2"

repositories {
    mavenCentral()
}

extra["ktorVersion"] = "1.6.7"

dependencies {
    api("io.ktor:ktor-client-okhttp:${property("ktorVersion")}")
    api("io.ktor:ktor-client-serialization:${property("ktorVersion")}")
    implementation(kotlin("stdlib"))
    testImplementation(kotlin("test"))
    testImplementation(kotlin("test-annotations-common"))
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    sourceCompatibility = "1.8"
}

tasks.withType<Wrapper> {
    gradleVersion = "6.1.1"
    distributionType = Wrapper.DistributionType.ALL
}