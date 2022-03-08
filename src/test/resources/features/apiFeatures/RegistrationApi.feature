
  Feature: Registrant api test


    #@ApiRegistrant
    Scenario Outline: registrant test

      Given user sets the necessary path params
      And user sets the expected data "<firstname>" , "<lastname>" "<SSN>" "<email>" , "<username>" ,"<password>" , "<lan>"
      And user sends the request and gets the response
      When user saves the api records to correspondent files
      Then user validates api records

      Examples: api test data
        | firstname | lastname | SSN | email | username | password | lan |
        | Ali | Akyurt | 234-65-6543 | ali@gmail.com | aliakyurt | Ali123345, | en |


    @ApiRegistrant
      Scenario: api get request for users (registrant)
        #given().headers("Authorization","Bearer "+ token, "Content-Type", ContentType.JSON,"Accept", ContentType.JSON).when().get(endpoint);

      Given user sends the get request for users data
        And user deserialized data to java
        And user saves the users data to correspondent files