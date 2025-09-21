Feature: Create The userlist of book

Scenario: Create book list

Given  Provide pay load for create user
When  Hit the post request 
Then  Validate the statuscode 200
And Validate all response body 
#
#Scenario: upadting the request
#Given provide upadted payload request
#When Hit the put type of request
#Then validate status code and repsonse

Scenario: Update the booking
Given  Provide small update payload
When hit the patch request
Then validate repsonse thorugh desrilization


