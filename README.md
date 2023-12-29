![GitHub License](https://img.shields.io/github/license/Aquerr/steam-web-api-client)
![Maven Central](https://img.shields.io/maven-central/v/io.github.aquerr/steam-web-api-client)

# Steam Web Api Client

This library provides a simple way to interact with Steam Web Api through Java.

## Features

* Query Steam Workshop
* Get information about users/players.
* And more...

## Usage

#### Maven

`steam-web-api-client` is available at Maven Central.
```
<dependencies>
    <dependency>
        <groupId>io.github.aquerr</groupId>
        <artifactId>steam-web-api-client</artifactId>
        <version>${steam-web-api-client.version}</version>
    </dependency>
</dependencies>
```

It is also possible to get the library via Jitpack.

#### Maven
```
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>

<dependencies>
  <dependency>
    <groupId>io.github.aquerr</groupId>
    <artifactId>steam-web-api-client</artifactId>
    <version>main-SNAPSHOT</version>
  </dependency>
</dependencies>
```

#### Gradle

Groovy DSL:
```
repositories {
    maven { url 'https://jitpack.io' }
}

dependencies {
    implementation 'com.github.Aquerr:steam-web-api-client:version' // <-- Via Maven Central
    implementation 'com.github.Aquerr:steam-web-api-client:main-SNAPSHOT' // <-- Via Jitpack
}
```

Kotlin DSL:
```
repositories {
    mavenCentral()
    maven("https://jitpack.io")
}

dependencies {
    implementation("io.github.aquerr:steam-web-api-client:version") // <-- Via Maven Central
    implementation("io.github.aquerr:steam-web-api-client:main-SNAPSHOT") // <-- Via Jitpack
}
```

## Credits

Lead Developer - [Aquerr](https://github.com/Aquerr)

Developer - [mateo9x](https://github.com/mateo9x)

## License

[Apache License, Version 2.0](https://github.com/Aquerr/steam-web-api-client/blob/main/LICENSE.md)