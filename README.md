# Staff


### При разрабобтке используется

* java version "1.8"
* javac 1.8
* Sring Boot 2.3.0.RELEASE
* PostgreSQL 12.3, compiled by Visual C++ build 1914, 64 bit
* IntelliJ IDEA 2019.3.= (Ultimate Edition)
* Apache Maven 3.6.1

### Инструкция по разворчиванию окружения

создать базу данных в Postgres
с именем "staff_db"

```
git clone https://github.com/Abdy-t/staff.git
```

Открыть с помощью IntelliJ IDEA
* в файле application.properties
ввести свои данные 
* spring.datasource.url - путь к БД
* spring.datasource.username - имя пользователя
* spring.datasource.password - пароль

Запустить проект

Тестовые данные
* Админ (login: "admin@mail.ru"; password: "123")
* User (login: "user@mail.ru"; password: "123")

## Собрано с помощью

* [Maven](https://maven.apache.org/) - Dependency Management

## Автор

* https://github.com/Abdy-t/staff.git
