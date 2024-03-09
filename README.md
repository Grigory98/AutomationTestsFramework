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

<h2>Локальный запуск тестов</h2>
<p>Для полного запуска тестов из корневого каталога ввести команду: <b>mvn test -Duiusername="username" -Duipassword="password" -Dapiusername="username" -Dapipassword="password"</b></p>
<p>Для запуска api тестов: <b>mvn test -pl api -Dapiusername="username" -Dapipassword="password"</b></p>
<p>Для запуска ui тестов: <b>mvn test -pl ui -Duiusername="username" -Duipassword="password"</b></p>

<p>Отчёт генерируется в папку allure-results. Для генерации просмотра использовать команду: <b>allure serve</b></p>
