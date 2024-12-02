# Weather Info App 🌦️  

A modern and user-friendly weather application designed to fetch real-time weather data using the Open Weather API. It uses the device's current location or a user-selected district (Zila) to display detailed weather information. The app is built using **Clean Architecture** principles and follows the **MVVM pattern** to ensure scalability and maintainability.  

---

## 🌟 Features  

- **Current Weather**: Displays weather details such as temperature, condition, and icon based on the device's location.  
- **Search Functionality**: Users can search for districts (Zilas) and view their weather details.  
- **Dynamic Suggestions**: The search bar dynamically suggests matching Zilas as you type.  
- **Modern UI**: Designed with **Material 3** to provide an intuitive and responsive experience.
- **Hilt for Dependency Injection**: Efficient and scalable dependency management.  

---

### Project Setup
Clone the project and open it using Android Studio. Then open your `local.properties` file under `Gradle Scripts`. You need to specify the `APP_ID` in your `local.properties` file. Store your API secret in plain string file or Kotlin file is very risky. For security purpose it's better store in local.properties file than plain string/Kotlin file.

#### Use Sample API without creating account
Add below lines at the end of your `local.properties` file. Then run the project. You'll get dummy or static API response from Open Weather API.
```properties

#this is sample App ID of Open Weather API
APP_ID=b6907d289e10d714a6e88b30761fae22
```
#### Use Real APP ID after sign up and activation of your APP ID
After Sign up at the website collect your own `APP ID` from their [API Keys page](https://home.openweathermap.org/api_keys). Then add below lines with your APP ID at the end of `local.properties` file.
```properties

#this is real App ID of Open Weather API
APP_ID=YOUR_OWN_APP_ID
```
The APP ID will be fetched from the `build.gradle` file and stored in `BuildConfig`. The `Retrofit` API call will use the APP ID from `BuildConfig`.


---

## 🎥 Demonstration  

### 1. **Home Screen**  
Shows the current weather based on your location.  

<img src="https://github.com/user-attachments/assets/5b015530-f6f8-412c-a630-91ab52dc8f2d" alt="Home Screen" width="200">

### 2. **Search Screen**  
Search for districts (Zila's) with dynamic suggestions and update the weather details accordingly.  

<img src="https://github.com/user-attachments/assets/aac70be1-9d34-44d8-af18-7038ea06a666" alt="Search Screen" width="200">    


**Watch the Full Demo**: [Demo Video](https://www.youtube.com/shorts/v2d6dcUuH5Q)  

---
