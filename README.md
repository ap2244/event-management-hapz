### Prerequisites

- Android Studio
- Java 8 or above
- Kotlin

### Installation

1. **Download the project:**
    - unzip it to a directory of your choice.

2. **Open the project in Android Studio.**

3. **Sync the project with Gradle files.**

4. **Build and run the Project**

   ---------Go to Build > Rebuild Project.
   --------Android Studio will compile the project and show build results in the Build window.
   -------- Set Up an Emulator or Connect a Device via USD
   ---------Select the target device or emulator from the device dropdown menu at the top of Android
   Studio.
   ---------Click the Run button (green triangle) in the toolbar.
   Emulator: You can use the built-in Android emulator in Android Studio. To set it up, go to
   Tools > AVD Manager and create a new virtual device.
   Physical Device: Connect your Android device via USB and ensure Developer Options and USB
   Debugging are enabled.

6**APK path = hapztask\app\build\outputs\apk\debug\app-debug.apk**

### Brief Documentation
===========================================

### Architecture and Design Choices

MVVM Architecture: Used for separating concerns, where the ViewModel handles the business logic, and
the View are responsible for displaying data and handling user interactions.

Room Database: Chosen for its simplicity and integration with LiveData, providing a robust way to
handle local data persistence.

TypeConverters: Implemented to manage the storage and retrieval of complex data types like
List<String>.

Material Design: Applied for a modern and user-friendly interface. Material Components like
TextInputLayout and RecyclerView are used for better UI/UX.

### Additional Libraries or Tools Used

Room: For local database management.
Gson: For JSON serialization and deserialization.
Material Components: For UI components and styling.