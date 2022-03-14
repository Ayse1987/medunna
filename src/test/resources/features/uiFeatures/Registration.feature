Feature: Test all registrant data

  @UIRegistration
  Scenario Outline: test registration
    Given user provides ssn id "<SSN>"
    And  user provides firstname and lastname "<firstname>" and "<lastname>"
    Then user creates username "<username>"
    And user provides also email "<email>"
    And user generates the password "<password>"
    And user registers and validates
    Then user creates the records to a correspondent file
    Examples: test data
      |SSN|firstname|lastname|username|email|password|
      |384-37-3827|Irfan|Pishkin|irfanpish|irfan@gmail.com|asdfA123.|

  @UIRegistration
    Scenario Outline: test password strength
    Given user provides the password "<password>"
    Then user validates the password strength "<strength>"
    Examples:
      | password | strength |
      |asdkfkfjs | 1        |
      |asdkfkfj? | 2        |
      |asdkfkfj?1| 3        |






    #=========================================
  #Feature: US01 Registration should be available using SSN, Firstname and Lastname
                #There should be api and DB validation




  Scenario: US01_TC01 valid ssn test
    Given go to medunna registration url "https://medunna.com/account/register"
    Then user provides ssn id "<SSN>"
    Then verify the SSN number is valid

  Scenario: US01_TC02 ssn blank test
    Given go to medunna registration url "https://medunna.com/account/register"
    And leave the SSN box blank
    Then verify SSN required message is displayed

  Scenario: US01_TC03 ssn without "-" test
    Given go to medunna registration url "https://medunna.com/account/register"
    And enter nine digit SSN without - ssn "123457896"
    Then verify the SSN invalid message is displayed


  Scenario Outline: US01_TC04 8 digit ssn test
    Given go to medunna registration url "https://medunna.com/account/register"
    And enter eight digit SSN "<ssn>"
    Then verify the SSN invalid message is displayed
    Examples:
      | ssn       |
      |45823698   |
      |342-25-968 |


  Scenario Outline: US01_TC05 10 digit ssn test
    Given go to medunna registration url "https://medunna.com/account/register"
    And enter ten digit SSN "<ssn>"
    Then verify the SSN invalid message is displayed
    Examples:
          | ssn       |
          |4582369810   |
          |342-25-96825 |

    Scenario Outline: US01_TC06 valid firstname test
      Given go to medunna registration url "https://medunna.com/account/register"
      And enter valid firstname "<firstName>"
      Then verify first name is valid
      Examples:
        | firstName   |
        |mahmut       |
        |1234567      |
        |Can123       |
        |/*.,Can12    |


  Scenario: US01_TC07 blank firstname test
    Given go to medunna registration url "https://medunna.com/account/register"
    And leave first name box blank
    Then verify first name required message is displayed


  Scenario Outline: US01_TC08 valid lastname test
    Given go to medunna registration url "https://medunna.com/account/register"
    And enter valid lastname "<lastName>"
    Then verify lastname is valid
    Examples:
      | lastName   |
      |Can          |
      |1234567      |
      |Can123       |
      |/*.,Can12    |

  Scenario: US01_TC09 blank lastname test
    Given go to medunna registration url "https://medunna.com/account/register"
    And leave last name blank
    Then verify last name required message is displayed














