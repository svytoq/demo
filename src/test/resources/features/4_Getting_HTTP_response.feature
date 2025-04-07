#noinspection NonAsciiCharacters
Feature: 4_Getting_HTTP_response

  Background:
    Given Kafka topic "demo.users" is clear
    And Db table "Users" is empty
    And Db table "Users" contains data:
      | id | name | email            |
      | 1  | John | john@example.com |
      | 2  | Anna | anna@example.com |
    And Minio buckets exist
      | demo-users |
    And Minio buckets are empty
      | demo-users |

  Scenario: Get users from http
    When User sends GET request with url "http://localhost:8080/api/users"
    Then Server returns "User" list by node ""
      | id | name | email            |
      | 1  | John | john@example.com |
      | 2  | Anna | anna@example.com |
    