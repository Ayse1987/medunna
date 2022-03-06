
  Feature: appointment test

    @Appointment
    Scenario Outline: test appointments
      Given user cliks on make an appointment
      And user provides the appointment name "<firstname>"
      And user provides the appointment lastname "<lastname>"
      And user provides ssn and email "<ssn>" and "<email>"
      And user provides the phone number "<phoneNumber>"
      And user provides the date "<date>"
      Then user requests appointment and verifies the success message
      Examples: test data
        | firstname | lastname | ssn | email | phoneNumber | date |
        | Adnan |  | 123-45-8765 | adnan@gmail.com | 234-564-8765 | 05-06-2022 |
