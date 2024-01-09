# Movies App

## Structural Design Pattern

The app is built with the Model-View-ViewModel (MVVM) is its structural design pattern that separates objects into three distinct groups:
- Models hold application data. They’re usually structs or simple classes.
- Views display visual elements and controls on the screen. They’re typically subclasses of UIView.
- View models transform model information into values that can be displayed on a view. They’re usually classes, so they can be passed around as references.

## Setup Requirements
Obtain your key from TMDB API and replace it in the Constants file:

API_KEY = "API_KEY_VALUE"

## Tech Stack

Kotlin - Kotlin is a programming language that can run on JVM. Google has announced Kotlin as one of its officially supported programming languages in Android Studio; and the Android community is migrating at a pace from Java to Kotlin.

### Jetpack components

**Jetpack Compose** - Jetpack Compose is Android’s modern toolkit for building native UI. It simplifies and accelerates UI development on Android. Quickly bring your app to life with less code, powerful tools, and intuitive Kotlin APIs.

- Android KTX - Android KTX is a set of Kotlin extensions that are included with Android Jetpack and other Android libraries. KTX extensions provide concise, idiomatic Kotlin to Jetpack, Android platform, and other APIs.

- AndroidX - Major improvement to the original Android Support Library, which is no longer maintained.

- Lifecycle - Lifecycle-aware components perform actions in response to a change in the lifecycle status of another component, such as activities and fragments. These components help you produce better-organized, and often lighter-weight code, that is easier to maintain.

- ViewModel -The ViewModel class is designed to store and manage UI-related data in a lifecycle conscious way.

- LiveData - LiveData is an observable data holder class. Unlike a regular observable, LiveData is lifecycle-aware, meaning it respects the lifecycle of other app components, such as activities, fragments, or services. This awareness ensures LiveData only updates app component observers that are in an active lifecycle state.

- Paging 3 library - The Paging library helps you load and display pages of data from a larger dataset from local storage or over network. This approach allows your app to use both network bandwidth and system resources more efficiently.

- Room database - The Room persistence library provides an abstraction layer over SQLite to allow fluent database access while harnessing the full power of SQLite. 

**Kotlin Coroutines** - A concurrency design pattern that you can use on Android to simplify code that executes asynchronously.

**Retrofit** - Retrofit is a REST client for Java/ Kotlin and Android by Square inc under Apache 2.0 license. Its a simple network library that is used for network transactions. By using this library we can seamlessly capture JSON response from web service/web API.

**GSON** - JSON Parser,used to parse requests on the data layer for Entities and understands Kotlin non-nullable and default parameters.

**Kotlin Flow** - In coroutines, a flow is a type that can emit multiple values sequentially, as opposed to suspend functions that return only a single value.

**Dagger Hilt** - A dependency injection library for Android that reduces the boilerplate of doing manual dependency injection in your project.

**Ramcosta Navigation Library** - A KSP library that processes annotations and generates code that uses Official Jetpack Compose Navigation under the hood. It hides the complex, non-type-safe and boilerplate code you would have to write otherwise.

**Logging Interceptor** - logs HTTP request and response data.

**Coil** - An image loading library for Android backed by Kotlin Coroutines.

**Timber** - A logger with a small, extensible API which provides utility on top of Android's normal Log class.

### Home Screen

<img src="https://github.com/EliYakubov7/Movies-App/blob/main/screenshots/home_screen.jpg" width="250">

### Search Screen

<img src="https://github.com/EliYakubov7/Movies-App/blob/main/screenshots/search_screen.jpg" width="250">

### Details Screen 

<img src="https://github.com/EliYakubov7/Movies-App/blob/main/screenshots/details_screen.jpg" width="250">

### Favorites Screen 

<img src="https://github.com/EliYakubov7/Movies-App/blob/main/screenshots/favorites_screen.jpg" width="250">
