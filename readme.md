[![Build Status](https://secure.travis-ci.org/ushkinaz/webservice-client.png?branch=master)](http://travis-ci.org/ushkinaz/webservice-client)

# Задание
[смотри тут](assignment.md)
 
# Запуск
Кроме java 8 не нужно ничего

Win/Mac/Linux:

`gradlew bootRun`

endpoint:
[http://localhost:8080/clients](http://localhost:8080/clients)

Postman коллекция с [тестами](https://www.getpostman.com/collections/b47edc98c53b4aeb53c7) 

# База данных

h2 in memory
конслоль доступна по [http://localhost:8080/h2-console](http://localhost:8080/h2-console)
Параметры:

```
driver: org.h2.Driver
url: jdbc:h2:mem:testdb
user: sa
```

# Особенности реализации
* Сделано в предположении, что нагрузки небольшие
* Для упрощения тестового запуска база данных хранится в памяти, 
* Пароли в базе хранятся с солью, хотя на первый взгляд это не так :)
* Логин сделан регистро-независимый
* Юнит тесты сделаны только для технически небанальной части, которая может сломаться
* Интеграционные тесты в данном случае проигнорированы, кроме представленных в Postman коллекции

# Пути развития в настоящий проект
1. Включить нормальную БД
2. Включить кеширование
3. Создать промежуточный уровень DTO бинов. Заполняются из запросов, проходят валидацию,
 уходят во внутренние сервисы. Кроме прочих плюшек, такой подход упрощает изменение внешнего API.
4. Разнести ClientController на сервисы
