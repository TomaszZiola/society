import org.gradle.api.JavaVersion.VERSION_21

plugins {
    java
    jacoco
    checkstyle
    id("org.springframework.boot") version "3.2.0"
    id("io.spring.dependency-management") version "1.1.4"
    id("io.freefair.lombok") version "8.4"
    id("org.flywaydb.flyway") version "10.4.0"
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
    implementation("org.postgresql:r2dbc-postgresql:1.0.3.RELEASE")
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

    classDirectories.setFrom(
            classDirectories.files.filter { file ->
                val filePath = file.toString()
                !filePath.contains("com/crux/society/SocietyApplication.class")
            }
    )
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

    classDirectories.setFrom(
            classDirectories.files.filter { file ->
                val filePath = file.toString()
                !filePath.contains("com/crux/society/SocietyApplication.class")
            }
    )
}

checkstyle {
    configFile = file("${rootDir}/checkstyle.xml")
}
