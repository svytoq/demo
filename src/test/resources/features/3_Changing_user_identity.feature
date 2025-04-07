#noinspection NonAsciiCharacters
Feature: 3_Changing_user_identity

  Background:
    Given Kafka topic "demo.users" is clear
    And Db sequence "user_id_seq" reset to 1
    And Db table "Users" is empty
    And Db table "Users" contains data:
      | id | name | email            |
      | 1  | John | john@example.com |
    And Minio buckets exist
      | demo-users |
    And Minio buckets are empty
      | demo-users |

  Scenario: Changing user John email from http
    When User sends "PUT" request with url "http://localhost:8080/api/users/1"
      """
     {
        "name": "John",
        "email": "johnBestJavaCoder@example.com"
     }
      """
    Then Kafka topic "demo.users" receives message in 5000 millis
      """
     {
        "name": "John",
        "email": "johnBestJavaCoder@example.com"
     }
      """
    And Db table "Users" has data:
      | id | name | email            |
      | 1  | John | johnBestJavaCoder@example.com |
    And File "John_johnBestJavaCoder@example.com.txt" exists in bucket "demo-users" in 5000 millis