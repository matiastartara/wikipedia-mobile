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

## 🚀 Getting Started

### Prerequisites
- **Java 17** or higher.
- **Maven** installed and configured.
- **Appium Server** (v2.0+ recommended).
- **Android SDK** (for Emulator or Physical Device).

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

