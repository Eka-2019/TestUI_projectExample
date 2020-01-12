Narrative:
This story covers a basic tests of signup page

Lifecycle:
Before:
Given I open singup page


Scenario: Type Invalid Year
When I set date0:
|month   |day|year|
|December|20 |23  |
|May     |15 |85  |
And I set check sharing_checkbox
Then I should see error: "Please enter a valid year."
And I should not see error: "Please enter a valid day of the month."
And I should not see error: "Please enter your birth month."
And I should not see error: "When were you born?"


Scenario: Type Not Matched Emails
When I set email "bolotova-katya@gmail.com"
And I set confirmation_email "bolotova.katya@gmail.com"
And I set name "TestName"
And Click signUp button
Then I should see error: "Email address doesn't match."


Scenario: Type Empty Password
When I set email "bolotova-katya@gmail.com"
And I set confirmation_email "bolotova.katya@gmail.com"
And I set name "TestName"
And Click signUp button
Then I should see error: "Enter a password to continue."


Scenario: Type Invalid Values
When I set email "bolotova.katya@gmail."
And I set name "TestName"
And I set sex "Female"
And I set uncheck sharing_checkbox
And Click signUp button
Then I see "8" errors messages
And Number "1" error has text: "The email address you supplied is invalid."
And Number "4" error has text: "Please enter your birth month."
And Number "5" error has text: "Please enter a valid day of the month."
And Number "8" error has text: "Please confirm you're not a robot."