#Author: your.email@your.domain.com
#Keywords Summary : validating fetaures of place holder 

Feature: Validating mock api

  Scenario: Mock api is created or not 
    Given add place pay load 
    When create user with post request
    Then  validate api request is sucessfully created or not 
    
  
  Scenario: validate schema 
 		Given add place pay load 
    When create user with post request
    Then validate schema response is matching
    
    
  Scenario: Validate mock API response
    When I send GET request to "/api/user/123"
    Then the response status code should be 200
    And the response should contain "Annappa"

