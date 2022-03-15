
  Feature: Staff edit appointment
    Background:
      Given go to login page "https://medunna.com/login"
      And sign in as doctor: username "CaliskanAdnan", password "Adnan20."
      And navigate to my appointments page
      And enters patient's SSN to SSN box "433-42-3434"
      And find the patient and click edit button, patient id:"17193"


    Scenario: US21_TC01 Staff updates appointment
    And edit the patient appointment date and cliks save button
    Then verify the appointment updated message


