<h1 align="center">ShoopApp</h1></br>
<p align="center">  
A dummy shopping app for learning how to use MVVM + Clean Architecture
</p></br>

<p align="center">
  <a href="https://android-arsenal.com/api?level=24"><img alt="API" src="https://img.shields.io/badge/API-24%2B-brightgreen.svg?style=flat"/></a>
  <a href="https://github.com/mustfaunlu"><img alt="Profile" src="https://img.shields.io/badge/github-mustfaunlu-blue"/></a> 
</p>


## Screenshots
<p align="center">
<img src="/previews/login-screen.png" width="20%"/>
<img src="/previews/login-screen-loading.png" width="20%"/>
<img src="/previews/product-list-screen.png" width="20%"/>
<img src="/previews/product-list-screen-1.png" width="20%"/>
<img src="/previews/category-screen.png" width="20%"/>
<img src="/previews/product-detail-screen.png" width="20%"/>
<img src="/previews/add-to-cart.png" width="20%"/>
<img src="/previews/shopping-list.png" width="20%"/>
<img src="/previews/shopping-list-1.png" width="20%"/>
</p>

## App Gif
<p align="center">
<img src="/previews/app.gif" width="30%"/>
</p>

## Tech stack & Open-source libraries
- Minimum SDK level 24
- 100% [Kotlin](https://kotlinlang.org/) based + [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) and [Flow](https://developer.android.com/kotlin/flow) & [LiveData](https://developer.android.com/topic/libraries/architecture/livedata)
- [Android Architecture Components](https://developer.android.com/topic/libraries/architecture) - Collection of libraries that help you design robust, testable, and maintainable apps.
    -  A single-activity architecture, using the [Navigation Component](https://developer.android.com/guide/navigation) to manage fragment navigation operations.
    - [Lifecycle](https://developer.android.com/topic/libraries/architecture/lifecycle) - perform an action when lifecycle state changes
    - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - Stores UI-related data that isn't destroyed on UI changes.
    - [UseCases](https://developer.android.com/topic/architecture/domain-layer) - Located domain layer that sits between the UI layer and the data layer.
    - [Repository](https://developer.android.com/topic/architecture/data-layer) - Located in data layer that contains application data and business logic.
- [Android Hilt](https://developer.android.com/training/dependency-injection/hilt-android) - Dependency Injection Library
- [Retrofit](https://square.github.io/retrofit/) A type-safe HTTP client for Android and Java
- [OkHttp](https://square.github.io/okhttp/) An HTTP client that efficiently make network requests
- [Glide](https://github.com/bumptech/glide) An image loading and caching library for Android focused on smooth scrolling
- [Moshi](https://github.com/square/moshi) Moshi is a modern JSON library for Android, Java and Kotlin. It makes it easy to parse JSON into Java and Kotlin classes.
- [Room](https://developer.android.com/training/data-storage/room) The Room persistence library provides an abstraction layer over SQLite to allow for more robust database access while harnessing the full power of SQLite.

## Architecture
This app uses [***MVVM (Model View View-Model)***](https://developer.android.com/jetpack/docs/guide#recommended-app-arch) architecture

![](https://user-images.githubusercontent.com/21035435/69536839-9f4c8e80-0fa0-11ea-85ee-d7823e5a46b0.png)

## API
ShoopApp uses [DummyJson](https://dummyjson.com/) With FakeProductsAPI, what you get is different types of REST Endpoints filled with JSON data which you can use in developing the frontend with your favorite framework and library without worrying about writing a backend.
