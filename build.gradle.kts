import org.gradle.api.JavaVersion.VERSION_21

plugins {
    java
    jacoco
    checkstyle
    id("org.springframework.boot") version "3.2.2"
    id("io.spring.dependency-management") version "1.1.4"
    id("io.freefair.lombok") version "8.4"
}

group = "com.crux"

java {
    sourceCompatibility = VERSION_21
}

repositories {
    maven {
        url = uri("https://repo.spring.io/snapshot")
    }
    mavenCentral()
    mavenLocal()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.flywaydb:flyway-core")
    runtimeOnly("org.postgresql:postgresql")
    testImplementation("org.springframework.boot:spring-boot-testcontainers")
    testImplementation("org.testcontainers:junit-jupiter")
    testImplementation("org.testcontainers:postgresql:+")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.mockito:mockito-core:+")
}

tasks.test {
    useJUnitPlatform()
    finalizedBy(tasks.jacocoTestCoverageVerification)
}

tasks.check {
    dependsOn(
            project.tasks.jacocoTestCoverageVerification,
            project.tasks.jacocoTestReport
    )
}

val excludeDirectories = listOf("**/config/**", "**/models/**", "**/repositories/**")
val classToExclude = "com/crux/society/SocietyApplication.class"

fun excludePackageFromJacocoTask(task: JacocoReportBase, packages: List<String>) {
    task.classDirectories.setFrom(
            project.files(task.classDirectories.files.flatMap { file ->
                val fileTree = project.fileTree(file)
                fileTree.exclude(packages)
                fileTree.exclude(listOf(classToExclude))
                fileTree.files
            })
    )
}

tasks.jacocoTestReport {
    excludePackageFromJacocoTask(this, excludeDirectories)
}

tasks.jacocoTestCoverageVerification {
    violationRules {
        rule {
            limit {
                minimum = "0.99".toBigDecimal()
            }
        }
    }
    excludePackageFromJacocoTask(this, excludeDirectories)
}

checkstyle {
    configFile = file("${rootDir}/checkstyle.xml")
}
