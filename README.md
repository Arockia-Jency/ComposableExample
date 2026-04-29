# 📱 Login App with Jetpack Compose

A modern Android application built using **Jetpack Compose** that demonstrates a complete authentication flow along with dynamic UI components like list and grid views.

---

## ✨ Features

### 🔐 Authentication

* Email & Password login
* Input validation (email format & password length)
* Error handling with visual feedback (shake animation)
* "Remember Me" functionality using `SharedPreferences`
* Persistent login session

### 🏠 Home Screen

* Welcome screen after login
* Logout functionality
* Dynamic UI rendering:

  * 📜 Vertical List (LazyColumn)
  * 🔳 Horizontal Grid (LazyRow)

---

## 🧱 Tech Stack

* **Kotlin**
* **Jetpack Compose**
* **Material 3**
* **SharedPreferences** (Session Management)

---

## 📂 Project Structure

```
com.example.loginwithcomposable
│
├── ui
│   ├── screen
│   │   ├── LoginScreen.kt
│   │   └── HomeScreen.kt
│   │
│   ├── components
│   │   ├── CustomTextField.kt
│   │   └── HeaderImage.kt
│
├── utils
│   ├── PrimaryButton.kt
│   ├── ValidationUtils.kt
│   └── Extensions (shake animation)
│
├── helper
│   └── SessionManager.kt
```

---

## 🔄 App Flow

1. User enters email & password
2. Validation is applied
3. If "Remember Me" is checked:

   * Credentials are saved
   * Session is persisted
4. On successful login → Navigate to Home Screen
5. Logout:

   * Clears login state
   * Preserves saved data only if "Remember Me" is enabled

---

## 🧠 Key Concepts Used

* State management with `remember` & `mutableStateOf`
* Custom composables for reusable UI
* Navigation callbacks
* Persistent storage using `SharedPreferences`
* Lazy layouts:

  * `LazyColumn` → vertical list
  * `LazyRow` → horizontal scroll

---

## 🎨 UI Highlights

* Clean login UI with header image
* Real-time validation feedback
* Smooth scrolling experience
* Responsive layout using Compose

---

## 🚀 Getting Started

### Prerequisites

* Android Studio (latest version)
* Kotlin support enabled

### Steps

1. Clone the repository
2. Open in Android Studio
3. Run the project on emulator/device

---

## 📌 Future Improvements

* 🔐 API-based authentication
* 🧾 Form validation enhancements
* 🌙 Dark mode support
* 🔄 Navigation using Navigation Compose
* 🖼️ Image-based grid (like gallery apps)

---

## 🙌 Contribution

Feel free to fork this repo and improve it. Contributions are always welcome!

---

## 📄 License

This project is open-source and available under the MIT License.

---

## 👨‍💻 Author

Developed as a learning project for mastering **Jetpack Compose UI & State Management**.
