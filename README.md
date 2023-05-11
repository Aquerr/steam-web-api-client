# Steam Web Api Client

This library provides a simple way to interact with Steam Web Api through Java.

## Features

* Query Steam Workshop
* And more...

## Usage

The library is currently available via jitpack.

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
    implementation 'com.github.Aquerr:steam-web-api-client:main-SNAPSHOT'
}
```

Kotlin DSL:
```
repositories {
    maven("https://jitpack.io")
}

dependencies {
    implementation("com.github.Aquerr:steam-web-api-client:main-SNAPSHOT")
}
```

## Credits

Aquerr (Bartłomiej Stępień)

## License

[Apache License, Version 2.0](https://github.com/Aquerr/steam-web-api-client/blob/main/LICENSE.md)