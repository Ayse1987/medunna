
  Feature: Registrant api test


    @apiRegistrant
    Scenario Outline: registrant test

      Given user sets the necessary path params
      And user sets the expected data "<firstname>" , "<lastname>" "<SSN>" "<email>" , "<username>" ,"<password>" , "<lan>"
      And user sends the request and gets the response
      When user saves the api records to correspondent files
      Then user validates api records

      Examples: api test data
        | firstname | lastname | SSN | email | username | password | lan |
        | Ali | Akyurt | 234-65-6543 | ali@gmail.com | aliakyurt | Ali123345, | en |


      Scenario: api get request
        #given().headers("Authorization","Bearer "+ token, "Content-Type", ContentType.JSON,"Accept", ContentType.JSON).when().get(endpoint);
        Given