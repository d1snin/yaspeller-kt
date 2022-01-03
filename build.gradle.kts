plugins {
    kotlin("jvm") version "1.6.10"
    kotlin("plugin.serialization") version "1.4.21"
}

group = "uno.d1s"
version = "0.0.1-alpha.0"

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