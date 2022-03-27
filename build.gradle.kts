plugins {
    id("maven-publish")
    id("java-library")
    kotlin("jvm") version "1.6.10"
    kotlin("plugin.serialization") version "1.4.21"
}

group = "dev.d1s"
version = "0.2.3-beta.1"

repositories {
    mavenCentral()
    maven(url = "https://jitpack.io/")
}

val gsonVersion: String by project
val fuelVersion: String by project
val coroutinesVersion: String by project
val striktVersion: String by project

dependencies {
    implementation("com.google.code.gson:gson:$gsonVersion")
    implementation("com.github.kittinunf.fuel:fuel:$fuelVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")
    implementation(kotlin("stdlib"))
    testImplementation(kotlin("test"))
    testImplementation(kotlin("test-annotations-common"))
    testImplementation("io.strikt:strikt-jvm:$striktVersion")
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    sourceCompatibility = "1.8"
}

publishing {
    publications {
        create<MavenPublication>("yaspeller-kt") {
            from(components["java"])
        }
    }
}

kotlin {
    explicitApiWarning()
}