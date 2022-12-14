plugins {
    kotlin("jvm") version "1.7.0"

    id("io.kotest.multiplatform") version "5.0.2"
}

group = "me.antonbogomazov"
version = "1.0-SNAPSHOT"

val kotestVersion = "5.5.4"
val mockkVersion = "1.13.3"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("io.kotest:kotest-runner-junit5:$kotestVersion")
    testImplementation("io.kotest:kotest-property:$kotestVersion")
    testImplementation("io.mockk:mockk:$mockkVersion")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}
