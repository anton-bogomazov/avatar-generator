plugins {
    kotlin("jvm") version "1.7.0"
}

group = "me.antonbogomazov"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

// todo move to common config with versions
val kotestVersion = "5.5.4"
val mockkVersion = "1.13.3"

// todo move sharable deps to lib
dependencies {
    implementation("com.soywiz.korlibs.korim:korim:2.7.0")

    implementation(project(":common"))

    testImplementation("io.kotest:kotest-runner-junit5:$kotestVersion")
    testImplementation("io.kotest:kotest-property:$kotestVersion")
    testImplementation("io.mockk:mockk:$mockkVersion")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}
