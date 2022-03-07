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
}

val gsonVersion: String by project
val okhttpVersion: String by project
val coroutinesVersion: String by project

dependencies {
    implementation("com.google.code.gson:gson:$gsonVersion")
    implementation("com.squareup.okhttp3:okhttp:$okhttpVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")
    implementation(kotlin("stdlib"))
    testImplementation(kotlin("test"))
    testImplementation(kotlin("test-annotations-common"))
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