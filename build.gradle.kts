/*
 * This file was generated by the Gradle 'init' task.
 *
 * This generated file contains a sample Kotlin library project to get you started.
 */


plugins {
    // Apply the Kotlin JVM plugin to add support for Kotlin on the JVM.
    kotlin("jvm") version "1.3.40"
    `maven-publish`
}

repositories {
    // Use jcenter for resolving dependencies.
    // You can declare any Maven/Ivy/file repository here.
    jcenter()

    maven {
        url = uri("gcs://mikifi-artifacts/maven2")
    }
}

dependencies {
    implementation("io.protostuff", "protostuff-core", "1.6.0")
    implementation("com.fasterxml.jackson.module", "jackson-module-kotlin", "2.9.9")
    implementation("com.fasterxml.jackson.jaxrs", "jackson-jaxrs-json-provider","2.9.9")
    implementation("io.swagger.core.v3", "swagger-core" , "2.0.8")
    implementation("io.swagger.core.v3", "swagger-annotations" , "2.0.8")
    implementation("javax.validation", "validation-api", "2.0.1.Final")
    implementation(kotlin("stdlib-jdk8"))
    testImplementation(kotlin("test"))
    testImplementation(kotlin("test-junit"))
}

tasks {
    val sourcesJar by creating(Jar::class) {
        archiveClassifier.set("sources")
        from(sourceSets.main.get().allSource)
    }

    val javadocJar by creating(Jar::class) {
        dependsOn.add(javadoc)
        archiveClassifier.set("javadoc")
        from(javadoc)
    }

    artifacts {
        archives(sourcesJar)
        archives(javadocJar)
        archives(jar)
    }

    val tag by creating(Exec::class) {
        commandLine( "git", "tag", "${project.version}", "-a", "-m 'version ${project.version}'")
        commandLine("git", "push", "origin", "${project.version}")

    }
}

publishing {
    publications {
        create<MavenPublication>("myLibrary") {
            from(components["kotlin"])
            artifact(tasks["sourcesJar"])
            artifact(tasks["javadocJar"])
        }
    }

    repositories {
        maven {
            url = uri("gcs://mikifi-artifacts/maven2")
        }

        maven {
            name = "localRepo"
            url = uri("file://" + System.getProperty("user.home") + "/.m2/repository/")
        }
    }
}

open class GreetingTask : DefaultTask() {
    var greeting = "hello from GreetingTask"

    @TaskAction
    fun greet() {
        println(greeting)
    }
}