# National Bank Of Krakozhia

![example workflow](https://github.com/kaliavarun/NationalBankOfKrakozhia/actions/workflows/maven.yml/badge.svg)

## Technical Stack

- Spring Boot 2.5.1
- Java 11
- Vue JS
- HTML
- CSS
- SQL
- H2 Database

## Access

The application has been deployed on Heroku. Since Heroku doesn't support H2 database console, you can only access the
console by building the app locally.

- https://national-bank-of-krakozhia.herokuapp.com/
- A following default customer exists with id as following
  ```sh
    100000
    ```
- Create a new account with this user. You can create multiple accounts and access its details.

## Installation

1. Run maven install
    ```sh
    maven install
    ```
2. Access the UI at
    ```sh
    http://localhost:8081
    ```
3. Access the H2 Console Dashboard. user/pass -> sa/sa
    ```sh
    http://localhost:8081/h2db
    ```
   ![alt text](https://i.imgur.com/LNe6w67.jpeg)
    
