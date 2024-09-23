# Remittance App

This project is an Android app built using **Jetpack Compose** and follows the **MVVM (Model-View-ViewModel)** architecture. The app aims to provide a seamless user experience with asynchronous data fetching using **Kotlin Coroutines**.

## Design Decisions

1. **Jetpack Compose**: The app UI is entirely built using Jetpack Compose. This decision was driven by the modern declarative approach Compose offers, enabling faster and more flexible UI development compared to traditional XML layouts.
   
2. **MVVM Pattern**: The app follows the MVVM architecture, where:
   - The **ViewModel** holds the app’s data and business logic, ensuring separation of concerns.
   - The **View** (UI) observes changes in the data through Compose’s built-in state management, resulting in a reactive user interface.
   
3. **Coroutines**: Asynchronous tasks, such as network requests, are handled using Kotlin **coroutines**. This allows for non-blocking operations, ensuring that the app remains responsive while performing background tasks.

## Challenges Faced

One of the most significant challenges I encountered was implementing a **SharedViewModel** across multiple activities. The goal was to persist and share data throughout the order flow without re-initializing the `ViewModel` in each activity. After several iterations, I opted to use a singleton pattern within the `Application` class to manage the `ViewModel` and ensure consistency across activities.

This challenge was crucial because the app requires storing order details across different activities without data loss, and the standard lifecycle-aware `ViewModel` doesn’t naturally persist across activity transitions.

## Additional Notes

1. **Placeholder Images**: For the images of the recipients and wallets, I used **placeholder images** because the API currently does not return image data. In a production environment, this would ideally be replaced with actual image URLs provided by the backend.

2. **User Information**: The app temporarily stores user information using SharedPreferences. in a real case, this data should be securely fetched from an API and then, stored using SharedPref or even in a local database such as Room
