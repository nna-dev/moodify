<h1 align="center">Moodify</h1>
<p align="center">An android application inspired from Spotify</p>

## Tech stack and libraries
- [Kotlin](https://kotlinlang.org/docs/home.html), [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) + [Flow](https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/) for asynchronous
- [Material design 2](https://m2.material.io/design)
- Clean Architecture
- Gradle
  - Kotlin KTS
  - Version catalog
- [Retrofit](https://github.com/square/retrofit) & [OkHttp3](https://github.com/square/okhttp)
- [Sandwich](https://github.com/skydoves/sandwich)
- [Moshi](https://github.com/square/moshi)
- [Glide](https://github.com/bumptech/glide)
- [Timber](https://github.com/JakeWharton/timber)
- [Epoxy](https://github.com/airbnb/epoxy)

## Architecture

## Building the app
1. Create a Spotify account and log into the [Spotify Developer](https://developer.spotify.com).
2. Create an app and get `Client ID` and `Client Secret`.
3. Put your `Client ID` and `Client Secret` to the `local.properties` file.
```properties
SPOTIFY_CLIENT_ID=[Client ID]
SPOTIFY_CLIENT_SECRET=[Client Secret]
```
