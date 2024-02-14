Feature: Verify user able to launch the page and expected components are present in the page

	@landingpage
  Scenario: User able to launch the url and validate the components in landing page
    Given User able to navigate to given url in Config_Details_Hostproperties file
    And Validate below components present in the landing page
    
    | Community | School Query | Medical | Hawker Centres | btn2D3D | onemap3Btn | drawToolsSelected | shareview | getMyLoc | zoomInBtn | zoomInBtn |
    
    Then Close the browser
  