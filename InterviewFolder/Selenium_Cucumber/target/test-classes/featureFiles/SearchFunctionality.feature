Feature: Verify user able to launch the page search functionality
	@search
  Scenario: User able to launch the url and load the page
    Given User able to navigate to given url in Config_Details_Hostproperties file
  @search
  Scenario: Validate the search result
  	And Verify user able to search with "chinatown" in the search field
  	Then Verify search result window appeared and print the result in console 
  Then Close the browser