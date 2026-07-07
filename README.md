# 📱 Wikipedia Mobile Automation Project

[![Java](https://img.shields.io/badge/Java-17-orange.svg)](https://www.oracle.com/java/)
[![Maven](https://img.shields.io/badge/Maven-3.8+-blue.svg)](https://maven.apache.org/)
[![Appium](https://img.shields.io/badge/Appium-2.0-red.svg)](http://appium.io/)
[![TestNG](https://img.shields.io/badge/TestNG-7.8-brightgreen.svg)](https://testng.org/)

This project is a mobile automation framework designed for testing the **Wikipedia Android Application** using **Appium**, **Java**, and **TestNG**.

---

## 🏗️ Architecture

The framework follows a **Page Object Model (POM)** design pattern to ensure maintainability, reusability, and scalability.

- **Page Objects (`com.automation.pages`)**: Encapsulates the UI elements and behaviors of each application screen.
- **Test Scripts (`test`)**: Contains the test cases and validation logic, extending `BaseTest`.
- **Base Classes**:
    - `BasePage`: Provides generic methods for interactions like `click`, `type`, `swipe`, and `longPress`.
    - `BaseTest`: Manages driver lifecycle (`setUp`/`tearDown`) and report initialization.
- **Utilities (`com.automation.utils`)**: Helper classes for driver management (`DriverUtils`) and synchronization (`WaitUtils`).
- **Reporting (`com.automation.report`)**: Integrated with **ExtentReports** to generate rich HTML reports for test execution.

### Project Structure
```text
wikipedia-mobile/
├── src/
│   ├── main/java/com/automation/
│   │   ├── pages/        # Page Object classes
│   │   ├── report/       # ExtentReports configuration
│   │   └── utils/        # Driver and synchronization utilities
│   └── test/java/test/   # Test cases (SearchTest, etc.)
├── src/test/java/suite/  # TestNG suite XML files
└── src/main/resources/   # App binary and config properties
```

---

## 🛠️ Dependencies

The project manages its dependencies via **Maven**. Key libraries include:

| Dependency | Version | Description |
| :--- | :--- | :--- |
| **Appium Java Client** | 8.6.0 | For mobile interaction |
| **Selenium Java** | 4.15.0 | Core automation engine |
| **TestNG** | 7.8.0 | Test runner and assertions |
| **Extent Reports** | 5.1.1 | Visual HTML reporting |

---

## ✅ Requirements

Before running this project, make sure you have the following tools installed and properly configured:

### 1. 📦 Wikipedia APK
The APK is **not included in this repository** (binary files don't belong in Git). Download the exact version used in this project directly from the official Wikimedia releases server:

| Version | Date | Download |
| :--- | :--- | :--- |
| **50591** (tested) | 2026-06-02 | [wikipedia-50591-r-2026-06-02.apk](https://releases.wikimedia.org/mobile/android/wikipedia/stable/wikipedia-50591-r-2026-06-02.apk) |

> All stable releases are available at: https://releases.wikimedia.org/mobile/android/wikipedia/stable/

Once downloaded, place the APK in:
```
src/main/resources/org.wikipedia_50591.apk
```

---

### 2. 🤖 Android Emulator (Android Studio)
An **Android Virtual Device (AVD)** is required to run the tests on an emulated device.

- Download and install **[Android Studio](https://developer.android.com/studio)**.
- Open Android Studio → **Tools → Device Manager** (or AVD Manager).
- Create a new virtual device (e.g., **Pixel 6**, API Level 33+).
- Start the emulator before running any tests — the device must be booted and visible via `adb devices`.

> **Tip:** You can also launch the emulator directly from the terminal once the AVD is created:
> ```bash
> emulator -avd <your_avd_name>
> ```

---

### 3. ⚡ Appium Server
**Appium** must be installed globally and running as a server before executing the test suite.

- Install Appium via npm:
  ```bash
  npm install -g appium
  ```
- Install the **UiAutomator2 driver** (required for Android):
  ```bash
  appium driver install uiautomator2
  ```
- Verify your environment is correctly set up:
  ```bash
  appium doctor --android
  ```

---

### 4. 🔍 Appium Inspector
**Appium Inspector** is used to inspect mobile elements and obtain the locators (resource-id, xpath, accessibility id, etc.) needed to build the Page Object classes.

- Download it from: [https://github.com/appium/appium-inspector/releases](https://github.com/appium/appium-inspector/releases)
- Connect it to your running Appium server (`http://127.0.0.1:4723`) and start a session using the desired capabilities of your device.
- Use it to explore the app's UI hierarchy and copy element locators directly into your Page Objects.

---

## 💻 Commands

### Start the Android Emulator (from Android Studio)

1. Open **Android Studio**.
2. Go to **Tools → Device Manager**.
3. Click the ▶️ **Play** button next to your desired AVD to launch the emulator.
4. Wait until the device is fully booted (the home screen should be visible).
5. Confirm the device is recognized by running:
   ```bash
   adb devices
   ```
   Expected output:
   ```
   List of devices attached
   emulator-5554   device
   ```

---

### Start Appium Server (Terminal)

Open a terminal and run the following command to start the Appium server:

```bash
appium
```

By default, Appium listens on **port 4723**. You should see output like:

```
[Appium] Welcome to Appium v2.x.x
[Appium] Appium REST http interface listener started on http://0.0.0.0:4723
```

> **Important:** Keep this terminal open while running the tests. Closing it will stop the Appium server.

To start Appium on a custom port or host:
```bash
appium --port 4723 --address 127.0.0.1
```

---

## 🚀 Getting Started

### Installation
1. **Clone the repository:**
   ```bash
   git clone <repository-url>
   cd wikipedia-mobile
   ```

2. **Configure Environment:**
   Edit `src/main/resources/config.properties` to match your local setup:
   ```properties
   device.android.name=YourDeviceName
   run.ip=127.0.0.1
   run.port=4723
   ```

3. **Install Dependencies:**
   ```bash
   mvn clean install
   ```

---

## 🧪 Running Tests

> ⚠️ **Before running tests, make sure:**
> 1. The **Android Emulator** is running and visible via `adb devices`.
> 2. The **Appium server** is running in a separate terminal (`appium`).

### Via TestNG Suite (Recommended)
You can run specific test suites defined in the XML files:
```bash
mvn test -DsuiteXmlFile=src/test/java/suite/searchTest.xml
```

---

## 📊 Reports
After execution, the **ExtentReports** will be generated in the project root or specified directory. These reports provide a visual representation of:
- Passed/Failed test cases.
- Failure stack traces.
- Execution time.

---

## 🛠️ Useful Git Commands

| Action | Command |
| :--- | :--- |
| **Clone** | `git clone <url>` |
| **Create Branch** | `git checkout -b feature/your-feature` |
| **Check Status** | `git status` |
| **Add Changes** | `git add .` |
| **Commit** | `git commit -m "feat: add search tests"` |
| **Push** | `git push origin feature/your-feature` |

---
