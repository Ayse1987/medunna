
Feature: Registrant api test

  @smoke
@ApiRegistrant
    Scenario Outline: registrant test

      Given user sets the necessary path params
      And user sets the expected data "<firstname>", "<lastname>" "<SSN>" "<email>" "<username>" "<password>" and "<lan>"
      And user sends the POST request and gets the response
      When user saves the api records to correspondent files
      Then user validates api records

      Examples: api test data
        | firstname | lastname | SSN | email | username | password | lan |
        | Ali | Akyurt | 234-65-6543 | ali@gmail.com | aliakyurt | Ali123345, | en |


    @ApiRegistrant
      Scenario: api get request for users (registrant)
      Given user sends the get request for users data
        And user deserializes data to Java
        And user saves the users data to correspondent files



      #============================

    @ApiRegistrant
    Scenario: US01_TC10 Get all registrant information and validate
      Given set all registrant info to response
      And deserialize all registrant info to Java
      And save all registrant info to correspondent files
      Then validate registrants info contains "529-49-6464"

    @ApiRegistrant
    Scenario Outline: US01_TC11 Create registrants using api and validate
      Given user sets the necessary path params
      And user sets the expected data "<firstname>", "<lastname>" "<SSN>" "<email>" "<username>" "<password>" and "<lan>"
      And user sends the POST request and gets the response
      When user saves the api records to correspondent files
      Then user validates status code and api records

      Examples: api test data
        | firstname | lastname | SSN | email | username | password | lan |
        | Adnan | Arslan | 234-65-7579 | adnan01@gmail.com | adnan02 | Adnan01. | en |

    @ApiRegistrant
    Scenario: update existing users
      Given user sets the necessary path params for put request
      And user sets the expected user data
      And user makes a put request for users
      And user validates the changes


    @ApiRegistrant
    Scenario: delete existing user
      Given



