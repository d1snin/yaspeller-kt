plugins {
    id("maven-publish")
    id("java-library")
    kotlin("jvm") version "1.6.10"
}

group = "dev.d1s"
version = "1.0.1-stable.0"

java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
    mavenCentral()
    maven(url = "https://jitpack.io/")
}

val gsonVersion: String by project
val fuelVersion: String by project
val coroutinesVersion: String by project
val striktVersion: String by project
val mockkVersion: String by project
val teabagsVersion: String by project

dependencies {
    implementation("com.google.code.gson:gson:$gsonVersion")
    implementation("com.github.kittinunf.fuel:fuel:$fuelVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")
    implementation(kotlin("stdlib"))
    testImplementation(kotlin("test"))
    testImplementation(kotlin("test-annotations-common"))
    testImplementation("io.strikt:strikt-jvm:$striktVersion")
    testImplementation("io.mockk:mockk:$mockkVersion")
    testImplementation("dev.d1s.teabags:teabag-testing:$teabagsVersion")
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