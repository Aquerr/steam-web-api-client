plugins {
    `java-library`
    id("io.freefair.lombok") version "8.0.1"
    `maven-publish`
    id("signing")
}

group = "io.github.aquerr"
version = "1.0.0-SNAPSHOT"

repositories {
    mavenCentral()
}

java.toolchain.languageVersion.set(JavaLanguageVersion.of(JavaVersion.VERSION_17.majorVersion))

java {
    withSourcesJar()
    withJavadocJar()
}

dependencies {
    implementation(platform("com.fasterxml.jackson:jackson-bom:2.14.2"))
    implementation("com.fasterxml.jackson.core:jackson-databind")

    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.mockito:mockito-core:5.3.1")
    testImplementation("org.assertj:assertj-core:3.24.2")
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
                description.set("Steam Web Api Client built in Java.")
                artifactId = "steam-web-api-client"
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
}

signing {
    setRequired({
        (!project.version.toString().endsWith("SNAPSHOT"))
    })
    sign(publishing.publications["maven"])
}
