Feature: Verify user able see all search components present in the page
	@searchComponents
  Scenario: User able to launch the url and see the search panel
    Given User able to navigate to given url in Config_Details_Hostproperties file
  @searchComponents
  Scenario: Validate the search components
  	And Validate below components present in the search panel	
  |LandQueryInfo|droneInfo|nearbyQueryBtn|basemapsBtn|shadowMode|view360|moreSvcs|aboutInfo|
  	Then Close the browser
  	
  