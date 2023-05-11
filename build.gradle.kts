plugins {
    id("java")
    id("io.freefair.lombok") version "8.0.1"
    `maven-publish`
}

group = "io.github.aquerr"
version = "1.0.0-SNAPSHOT"

repositories {
    mavenCentral()
}

java.toolchain.languageVersion.set(JavaLanguageVersion.of(JavaVersion.VERSION_17.majorVersion))

dependencies {
    implementation(platform("com.fasterxml.jackson:jackson-bom:2.14.2"));
    implementation("com.fasterxml.jackson.core:jackson-databind")

    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
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
                url.set("https://github.com/Aquerr/steam-web-api-client")

                licenses {
                    license {
                        name.set("Apache License, Version 2.0")
                        url.set("https://www.gnu.org/licenses/gpl-3.0.en.html")
                    }
                }

                developers {
                    developer {
                        id.set("Aquerr")
                        name.set("Bartłomiej Stępień")
                        url.set("https://github.com/Aquerr/steam-web-api-client");
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