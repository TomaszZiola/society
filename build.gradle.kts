import org.gradle.api.JavaVersion.VERSION_21

plugins {
    java
    jacoco
    checkstyle
    id("org.springframework.boot") version "3.2.1-SNAPSHOT"
    id("io.spring.dependency-management") version "1.1.4"
    id("io.freefair.lombok") version "8.4"
}

group = "com.crux"

java {
    sourceCompatibility = VERSION_21
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.springframework.boot:spring-boot-starter-data-r2dbc")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.postgresql:r2dbc-postgresql:1.0.3.RELEASE")
    implementation("org.flywaydb:flyway-core")
    runtimeOnly("org.postgresql:postgresql")
    testImplementation("org.springframework.boot:spring-boot-testcontainers")
    testImplementation("org.testcontainers:junit-jupiter")
    testImplementation("org.testcontainers:postgresql:+")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("io.projectreactor:reactor-test")
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
