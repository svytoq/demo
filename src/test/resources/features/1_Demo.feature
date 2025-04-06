#noinspection NonAsciiCharacters
Feature: 1_Demo

  Background:
    Given Kafka topic "demo.users" is clear
    And Db table "Users" is empty
    And Minio buckets exist
      | demo-users |
    And Minio buckets are empty
      | demo-users |

  Scenario: Sending create user message1
    When User sends "POST" request with url "http://localhost:8080/api/users"
      """
     {
        "name": "John",
        "email": "john@example.com"
     }
      """
    Then Kafka topic "demo.users" receives message in 2000 millis
      """
      {
        "name": "John",
        "email": "john@example.com"
      }
      """
    And Db table "Users" has data:
      | id | name | email            |
      | 1  | John | john@example.com |
    And File "John_john@example.com.txt" exists in bucket "demo-users" in 5000 millis

  Scenario: Sending create user message2
    When User sends "POST" request with url "http://localhost:8080/api/users"
      """
     {
        "name": "Anna",
        "email": "anna.maria@test.org"
     }
      """
    Then Kafka topic "demo.users" receives message in 2000 millis
      """
      {
        "name": "Anna",
        "email": "anna.maria@test.org"
      }
      """
    And Db table "Users" has data:
      | id | name | email               |
      | 2  | Anna | anna.maria@test.org |
    And File "Anna_anna.maria@test.org.txt" exists in bucket "demo-users" in 5000 millis