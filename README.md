# Automation Infrastructure Project

## Overview
This project is a complete automation testing infrastructure built as a final course project.
The goal is to demonstrate understanding and practical implementation of real-world
test automation tools and frameworks.

The project combines web automation, parallel execution, reporting, logging, retry mechanisms,
custom wait conditions, and Selenium Grid integration.

---

## Technologies Used
- Java
- Maven
- Selenium WebDriver
- JUnit 5
- Selenium Grid (Docker)
- Report Portal (JUnit 5 integration)
- Logging (SLF4J + custom TestLogger)
- Retry mechanism (custom JUnit 5 extension)
- Explicit & custom waits (FluentWait + Conditions)
- Screenshots and page source capture on test failure

---

## Project Structure

### driver
- `MyDriverManager` – Manages WebDriver instances per thread using ThreadLocal.

### grid
- `SeleniumGridConfig` – Configures RemoteWebDriver instances for Chrome, Firefox, or Edge.
- `GridDriverManager` – Manages WebDriver instances specifically for Selenium Grid execution.

### logging
- `TestLogger` – Custom logger wrapper over SLF4J, supports MDC context and multiple log levels.

### wait
- `WaitManager` – Provides convenient methods for waiting for element visibility, clickability, text presence, or invisibility.
- `FluentWaitFactory` – Creates FluentWait instances with configurable timeouts.
- `WaitConfig` – Holds configurable short, medium, and long wait durations.
- `Conditions` – Custom reusable wait conditions.

### screenshot
- `TakeScreenshotOnFailure` – Annotation to mark tests for automatic screenshot/page source capture on failure.
- `ScreenshotUtils` – Utility to save screenshots and page sources to local filesystem.
- `screenshot.extension.ScreenshotExtension` – JUnit 5 extension to handle test results and integrate with Report Portal.

### retryAnnotation
- `annotations.Retry` – Custom annotation to retry failed tests a defined number of times.
- `extensions.RetryExtension` – JUnit 5 extension that implements retry logic based on the annotation.

---

## Features Implemented
- Web automation using Selenium WebDriver
- Parallel test execution using Selenium Grid
- Test execution management with JUnit 5
- Automatic retry on test failure with customizable attempts and delay
- Centralized logging using SLF4J and custom TestLogger with context support
- Custom wait utilities for stable and reliable tests
- Screenshots and page source capture on test failures
- Integration with Report Portal for automatic reporting and logs

---

## How to Run the Project

### Prerequisites
- Java 17 or higher
- Maven 3.6+
- Docker (for Selenium Grid)
- Browser drivers (if running locally)

### Run Selenium Grid
```bash
docker-compose up

mvn clean test
```
### Notes
- Tests will automatically capture screenshots and page source on failure.
- Retry mechanism will attempt failed tests based on the @Retry annotation settings.
- Logging is centralized; all test logs can be viewed in console or sent to Report Portal.
- Wait conditions are reusable and can be configured per test case.

### Example of Test Annotation Usage
```java
@TakeScreenshotOnFailure(screenshot = true, pageSource = true)
@Retry(attempts = 3, delay = 1000)
public void exampleTest() {
    WebDriver driver = MyDriverManager.getDriver();
    // test steps here
}
```

Additional Notes

This project was built incrementally throughout the course, applying each new topic
to the existing infrastructure. Each module demonstrates practical usage of the
technologies and best practices for test automation frameworks.