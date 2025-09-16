# NIT3213 Android App

Android application implementing the NIT3213 final assignment requirements with Hilt for dependency injection, Retrofit networking, MVVM architecture, RecyclerView UI and unit tests for key ViewModels.

## Project Setup

1. Open the project in **Android Studio** (Giraffe or newer).
2. Ensure the IDE uses **JDK 17**.
3. Sync Gradle files. The app module enables ViewBinding and uses the provided dependencies (Retrofit, OkHttp, Gson, Coroutines, Hilt, Material Components).
4. Build or run the app on a device/emulator running Android 7.0 (API 24) or later.

## Usage

1. Launch the app to open the Login screen.
2. Enter:
   - **Username**: your first name.
   - **Password**: your numeric student ID (without the leading `s`).
   - **Campus**: `footscray`, `sydney` or `br`. The input defaults to `br` if left empty.
3. Successful login calls `/{campus}/auth` and saves the returned keypass, which is then used to request `/dashboard/{keypass}`.
4. The Dashboard lists all returned entities with summaries (all properties except `description`). Tap an entity to open the Details screen which shows all fields, including description.

## Testing

Run unit tests for the ViewModels from Android Studio or the command line:

```bash
./gradlew test
```

The tests mock the repository layer and assert the `LoginViewModel` and `DashboardViewModel` state flows emit the expected `Result` values.
