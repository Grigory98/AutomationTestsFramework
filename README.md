# AutomationTestsFramework
[![Automated tests](https://github.com/Grigory98/AutomationTestsFramework/actions/workflows/run_tests.yml/badge.svg)](https://github.com/Grigory98/AutomationTestsFramework/actions/workflows/run_tests.yml)
<p>Проект с тестовыми фреймворками с api и ui автотестами.</p>

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
<ol>
  <li>Перейти в Actions -> Automated tests и заполнить требуемые поля</li>
  <li>Выбрать модуль для запуска (ui, api, all)</li>
  <li>Запустить джобу</li>
</ol>

<p>После прохождения джобы результаты тестов можно посмотреть на github pages: https://grigory98.github.io/AutomationTestsFramework/#</p>
<p>Срок хранения результатов 1 день.</p>

<h2>Запуск тестов</h2>
<b>Перед запуском тестов необходимо заполнить учетные данные в конфиг файлах!!!</b>
<p>1) Перейти в api/src/main/java/config/Constants.java и заполнить свойства LOGIN и PASSWORD</p>
<p>2) Перейти в ui/src/main/java/core/ApplicationConfig.java и заполнить свойства USERNAME и PASSWORD</p>

Для запуска тестов необходимо в корневом каталоге AutomationTestFramework запустить команду:
<p><b>mvn test</b></p>

<p>Allure отчеты генерируеются в каждом проекте отдельно в директории target/allure-results</p> 
Для генерации отчётов необходимо запустить команду:
<p><b>allure serve</b></p>
