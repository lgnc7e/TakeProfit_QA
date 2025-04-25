TakeProfit_QA

TakeProfit_QA is an automated testing project for web applications built using Java, Gradle, and Allure. The project follows the Page Object Model (POM) design pattern to ensure scalability and maintainability of tests.

📁 Project Structure
css
Копировать
Редактировать
TakeProfit_QA/
├── src/
│   ├── main/
│   │   └── java/
│   └── test/
│       └── java/
├── build.gradle
├── settings.gradle
├── gradlew
├── gradlew.bat
└── allure-results/
src/main/java/ — main application logic (if applicable).

src/test/java/ — test classes and page objects.

build.gradle — project build configuration.

allure-results/ — test results used to generate Allure reports.

🛠️ Technologies
Programming Language: Java

Build System: Gradle

Testing Framework: JUnit/TestNG (please specify which one)

Reporting: Allure

Architecture: Page Object Model (POM)

🚀 Getting Started
Prerequisites
Java (JDK 8 or higher)

Gradle installed (or use the included gradlew)

Allure CLI installed for report generation

Installation
Clone the repository:

bash
Копировать
Редактировать
git clone https://github.com/lgnc7e/TakeProfit_QA.git
cd TakeProfit_QA
Build the project:

bash
Копировать
Редактировать
./gradlew build
Running Tests
To run all tests:

bash
Копировать
Редактировать
./gradlew test
Generating Allure Report
After running the tests:

bash
Копировать
Редактировать
allure serve allure-results
🧪 Test Structure
Page Objects: Classes representing application pages, containing element definitions and interaction methods.

Test Classes: Contain test methods that use Page Objects to perform actions and assertions.

📄 License
This project is licensed under the MIT License. See the LICENSE file for details.
Тестовые классы: Содержат тестовые методы, использующие Page Objects для выполнения действий и проверок.

📄 Лицензия
Этот проект лицензируется под лицензией MIT. См. файл LICENSE для получения дополнительной информации.
