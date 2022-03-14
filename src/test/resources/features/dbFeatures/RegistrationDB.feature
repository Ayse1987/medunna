
  Feature: Registration_Database

    Background: create connection
      Given user creates a connection with db using "jdbc:postgresql://medunna.com:5432/medunna_db" , "medunnadb_user" and "Medunnadb_@129"

    @DBTest
    Scenario Outline:  db validations
      Given user sends the query to DB and gets the column data "<query>" and "<columnName>"
      And user saves the DB records to correspondent files
      Then user validates DB Registrant data
      Then user closes connection

      Examples: test data
        |query|columnName|
        |Select * from jhi_user|ssn|

    Scenario Outline: US01_12 Validate registrant SSN ids with DB
      Given get the registrants SSN numbers using "<query>" and "<column>"
      And user saves the DB records to correspondent files
      Then validate registrants SSNs contains "529-49-6464"
      Then user closes connection
      Examples: validate all
        | query                    | column |
        | Select * from jhi_user   | ssn    |





