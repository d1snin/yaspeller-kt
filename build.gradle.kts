plugins {
    id("maven")
    kotlin("jvm") version "1.6.10"
    kotlin("plugin.serialization") version "1.4.21"
}

group = "uno.d1s"
version = "0.2.0-beta.2"

repositories {
    mavenCentral()
}

extra["ktorVersion"] = "1.6.7"

dependencies {
    implementation(kotlin("stdlib"))
    implementation("io.ktor:ktor-client-okhttp:${property("ktorVersion")}")
    implementation("io.ktor:ktor-client-serialization:${property("ktorVersion")}")
    testImplementation(kotlin("test"))
    testImplementation(kotlin("test-annotations-common"))
}

tasks.withType<Jar> {
    from(configurations.compileClasspath.get().files.filter {
        it.exists()
    }.map {
        if (!it.isDirectory) {
            zipTree(it)
        } else {
            it
        }
    })
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    sourceCompatibility = "1.8"
}

tasks.withType<Wrapper> {
    gradleVersion = "6.1.1"
    distributionType = Wrapper.DistributionType.ALL
}