[![GitHub License](https://img.shields.io/github/license/Aquerr/steam-web-api-client)](https://github.com/Aquerr/steam-web-api-client/blob/main/LICENSE.md)
[![Maven Central](https://img.shields.io/maven-central/v/io.github.aquerr/steam-web-api-client)](https://central.sonatype.com/artifact/io.github.aquerr/steam-web-api-client/versions)

# Steam Web Api Client

This library provides a simple way to interact with Steam Web Api through Java.

## Features

* Query Steam Workshop
* Get information about users/players.
* And more...

# Prerequisites

* Java 17
* [Jackson (databind)](https://github.com/FasterXML/jackson-databind) (>3.x.x)
* [Steam Web Api Access Token](https://steamcommunity.com/dev) (required for most endpoints)

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
    <groupId>com.github.Aquerr</groupId>
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
    implementation 'io.github.aquerr:steam-web-api-client:version' // <-- Via Maven Central
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
    implementation("com.github.Aquerr:steam-web-api-client:main-SNAPSHOT") // <-- Via Jitpack
}
```

### Code Examples

#### Creating the SteamWebApiClient

```
String steamApiKey = "123mykey123"; // Your Steam Web Api Key
SteamWebApiClient steamWebApiClient = SteamWebApiClient.builder().apiKey(steamApiKey).build();

// As because api key is not required for all endpoints, this is also valid.
// Remember that endpoints that require api key will not work with such setup.
SteamWebApiClient steamWebApiClient = SteamWebApiClient.builder().build();
```

#### Using custom HttpClient and/or ObjectMapper
```
// Default HttpClient used by the library has timeout set to 5 seconds.
// Let's increase that timeout.
HttpClient httpClient = HttpClient.newBuilder()
        .connectTimeout(Duration.ofSeconds(10))
        .build();

// Default ObjectMapper does not fail on unknown properties, so let's change it to fail!
ObjectMapper objectMapper = JsonMapper.builder()
        .enable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
        .build();

String steamApiKey = "123mykey123"; // Your Steam Web Api Key
SteamWebApiClient steamWebApiClient = SteamWebApiClient.builder().apiKey(steamApiKey)
    .httpClient(httpClient)
    .objectMapper(objectMapper)
    .build();
```

#### Example workshop search

```
WorkShopQueryFilesRequest request = WorkShopQueryFilesRequest.builder()
        .appId(730) // Counter Strike App Id
        .searchText("Adventure COOP Maps")
        .fileType(WorkShopQueryFilesRequest.PublishedFileInfoMatchingFileType.ITEMS)
        .queryType(WorkShopQueryFilesRequest.PublishedFileQueryType.RANKED_BY_TOTAL_UNIQUE_SUBSCRIPTIONS)
        .key(steamApiKey) // Not required if you set up your SteamWebApiClient with an API Key.
        .numPerPage(10) // Number of items per page.
        .cursor("*") // We should set this to * for first request according to Steam Web Api Docs.
                     // After that use cursor from response that will get you next page.

        .returnPreviews(true) // If preview images should be returned.
        .build();
WorkShopQueryResponse response = steamWebApiClient.getSteamPublishedFileWebApiClient().queryFiles(request);
```

#### Get recently played games by a user

Note: If looked up user has private profile, and you are not friends, then the response will contain an empty list.
```
PlayerRecentlyPlayedGamesRequest request = PlayerRecentlyPlayedGamesRequest.builder()
        .steamId(1234L)
        .count(10)
        .build()

PlayerRecentlyPlayedGamesResponse response = steamWebApiClient.getPlayerWebApiClient().getRecentlyPlayedGames(request);
```

## Credits

Lead Developer - [Aquerr](https://github.com/Aquerr)

Developer - [mateo9x](https://github.com/mateo9x)

## License

[Apache License, Version 2.0](https://github.com/Aquerr/steam-web-api-client/blob/main/LICENSE.md)