plugins {
    id("maven-publish")
    id("java-library")
    kotlin("jvm") version "1.6.10"
    kotlin("plugin.serialization") version "1.4.21"
}

group = "uno.d1s"
version = "0.2.2-beta.2"

repositories {
    mavenCentral()
}

extra["gsonVersion"] = "2.8.9"
extra["okhttpVersion"] = "5.0.0-alpha.3"
extra["coroutinesVersion"] = "1.6.0"

dependencies {
    implementation("com.google.code.gson:gson:${property("gsonVersion")}")
    implementation("com.squareup.okhttp3:okhttp:${property("okhttpVersion")}")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:${property("coroutinesVersion")}")
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