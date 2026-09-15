# GroceryApp[cite: 6]

Welcome to **GroceryApp**, an Android application project[cite: 1, 6]. 

## Project Overview[cite: 1, 6]
* **Project Name**: GroceryApp[cite: 6]
* **Platform**: Android application with an `:app` module[cite: 5, 6].
* **Build System**: Gradle Wrapper containing startup scripts for POSIX systems[cite: 3] and Windows environments[cite: 4].

## Tech Stack & Configurations[cite: 1, 6]
* **Plugins**:
  * Android Application plugin[cite: 1].
  * Google Services plugin (`version 4.5.0`)[cite: 1].
  * Foojay Toolchains Resolver Convention plugin (`version 1.0.0`)[cite: 6].
* **Repository Management**: Repositories are centrally managed via `dependencyResolutionManagement` enforcing Google and Maven Central[cite: 6].
* **Build Optimizations (`gradle.properties`)**:
  * JVM arguments are set to `-Xmx2048m` with UTF-8 file encoding[cite: 2].
  * Gradle Configuration Cache is enabled (`org.gradle.configuration-cache=true`) to speed up build task execution[cite: 2].

## Getting Started[cite: 3, 4, 5]

To set up and run the project locally, follow these steps:

1. **Clone the repository** to your local workspace.
2. **Configure the Android SDK**[cite: 5]: 
   * Ensure your Android SDK is installed[cite: 5].
   * Create or update your `local.properties` file with your local SDK path (e.g., `sdk.dir=/path/to/your/android/sdk`)[cite: 5].
   * *Note*: `local.properties` contains machine-specific configurations and must not be checked into version control[cite: 5].
3. **Build the project** using the provided Gradle wrapper scripts[cite: 3, 4]:
   * For macOS / Linux[cite: 3]:
     ```bash
     ./gradlew build
     ```
   * For Windows[cite: 4]:
     ```cmd
     gradlew.bat build
     ```
