#noinspection NonAsciiCharacters
Feature: 5_Kafka_poducer

  Background:
    Given Kafka topic "demo.users" is clear
    And Db table "Users" is empty
    And Db sequence "user_id_seq" reset to 1
    And Minio buckets exist
      | demo-users |
    And Minio buckets are empty
      | demo-users |

  Scenario: Get users from kafka
    When External service sends message to kafka topic "demo.create.user"
    """
     {
        "name": "John",
        "email": "john@example.com"
     }
    """
    Then Kafka topic "demo.users" receives message in 5000 millis
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