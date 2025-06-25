plugins {
    `java-library`
    id("io.freefair.lombok") version "8.14"
    `maven-publish`
    id("signing")
}

group = "io.github.aquerr"
description = "Simple Java Steam Web API client."
version = "1.6.0-SNAPSHOT"

repositories {
    mavenCentral()
}

java.toolchain.languageVersion.set(JavaLanguageVersion.of(JavaVersion.VERSION_11.majorVersion))

java {
    withSourcesJar()
    withJavadocJar()
}

dependencies {
    api("com.fasterxml.jackson.core:jackson-databind:2.19.1")

    testImplementation(platform("org.junit:junit-bom:5.13.2"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    testImplementation("org.mockito:mockito-core:5.18.0")
    testImplementation("org.mockito:mockito-junit-jupiter:5.18.0")
    testImplementation("org.assertj:assertj-core:3.27.3")
    testImplementation("org.wiremock:wiremock:3.13.1")
}

tasks.test {
    useJUnitPlatform()
}

publishing {
    publications {
        create<MavenPublication>("maven") {

            from(components["java"])

            pom {
                name.set("Steam Web Api Client")
                artifactId = "steam-web-api-client"
                description.set(project.description)
                url.set("https://github.com/Aquerr/steam-web-api-client")

                licenses {
                    license {
                        name.set("Apache License, Version 2.0")
                        url.set("https://www.apache.org/licenses/LICENSE-2.0")
                    }
                }

                developers {
                    developer {
                        id.set("Aquerr")
                        name.set("Bartłomiej Stępień")
                        url.set("https://github.com/Aquerr")
                    }

                    developer {
                        id.set("mateo9x")
                        name.set("Mateusz P.")
                        url.set("https://github.com/mateo9x")
                    }
                }

                scm {
                    connection.set("scm:git:git://github.com/Aquerr/steam-web-api-client.git")
                    developerConnection.set("scm:git:ssh://github.com/Aquerr/steam-web-api-client.git")
                    url.set("https://github.com/Aquerr/steam-web-api-client")
                }
            }
        }
    }

    repositories {
        maven {
            name = "oss"
            url = uri("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")

            credentials {
                username = project.findProperty("ossrhUsername") as String?
                password = project.findProperty("ossrhPassword") as String?
            }
        }

        maven {
            name = "oss-snapshots"
            url = uri("https://s01.oss.sonatype.org/content/repositories/snapshots/")

            credentials {
                username = project.findProperty("ossrhUsername") as String?
                password = project.findProperty("ossrhPassword") as String?
            }
        }
    }
}

signing {
    setRequired({
        (!project.version.toString().endsWith("SNAPSHOT"))
    })
    sign(publishing.publications["maven"])
}