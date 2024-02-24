# AutomationTestsFramework

<h2>Проект содержит несколько модулей:</h2>
<ul>
  <li>api</li>
  <li>ui</li>
  <li>utils</li>
</ul>

UI модуль:
Использует JUnit, Maven, Selenium

API модуль:
Создан на основе библиотеки Apache HttpClient

Utils модуль:
Содержит в себе единственный класс для генерации названий.

Для запуска тестов необходимо в корневом каталоге AutomationTestFramework запустить команду:
mvn test -DtestFailureIgnore=true

Allure отчеты генерируеются в target/allure-results, для просмотра отчётов необходимо запустить команду allure serve
