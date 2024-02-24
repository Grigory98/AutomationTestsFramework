# AutomationTestsFramework

<h2>Проект содержит несколько модулей</h2>
<ul>
  <li>api</li>
  <li>ui</li>
  <li>utils</li>
</ul>

<h3>UI модуль:</h3>
Использует JUnit, Maven, Selenium

<h3>API модуль:</h3>
Создан на основе библиотеки Apache HttpClient

<h3>Utils модуль:</h3>
Содержит в себе единственный класс для генерации названий.

<h2>Запуск тестов</h2>
Для запуска тестов необходимо в корневом каталоге AutomationTestFramework запустить команду:
<p>mvn test -DtestFailureIgnore=true</p>

Allure отчеты генерируеются в каждом проекте отдельно в директории target/allure-results, для просмотра отчётов необходимо запустить команду: 
<p>allure serve</p>
