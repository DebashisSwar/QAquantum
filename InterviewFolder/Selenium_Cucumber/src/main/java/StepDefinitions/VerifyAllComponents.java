package StepDefinitions;

import java.util.List;

import QuantumInvertions.Selenium_Cucumber.accessConfigData;
import QuantumInvertions.Selenium_Cucumber.commonFunctions;
import dev.failsafe.internal.util.Assert;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class VerifyAllComponents {
	static commonFunctions reference = null;
	static accessConfigData configData = null;
	@Given("^User able to navigate to given url in Config_Details_Hostproperties file$")
    public void launchBrowser() {
		try {
			reference = new commonFunctions();
			configData = new accessConfigData();
			reference.openWebsite(configData.getBrowserUrl(),configData.getApplicationUrl());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	@And("^Validate below components present in the landing page$")
	public void component_verification (DataTable dataTable) throws Exception {
		List<List<String>> compMaps = dataTable.asLists(String.class);
		for (int itr=0; itr<11; itr++) {
			try {
			String property = "//*[@id='"+compMaps.get(0).get(itr)+"' or span[contains(text(),'"+compMaps.get(0).get(itr)+"')] or @class='"+compMaps.get(0).get(itr)+"']";
			reference.presenceOfElement(property, "xpath");
			} catch (Exception e) {
				System.out.println(compMaps.get(0).get(itr)+" Not found in the UI");
			}
		}
	}
	@And("^Verify user able to search with \"(.*)\" in the search field$")
	public void searchField_verification (String ipText) throws Exception {
		try {
		Thread.sleep(15000);
		//reference.javascriptClick("//*[@id='Community']", "xpath");
		//Thread.sleep(5000);
		reference.typeText("//input[@placeholder='Search OneMap']", "xpath", ipText);
		Thread.sleep(5000);
		reference.seleniumClick("//div[@class='autocom-box']/li[2]/*", "xpath");
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	@Then("^Verify search result window appeared and print the result in console$")
	public void validateSearchResult () throws Exception {
		try {
		Thread.sleep(5000);
		reference.presenceOfElement("//*[@id='markerInfoClose']/../../div[2]/span[1]", "xpath");
		reference.fetchText("//*[@id='markerInfoClose']/../../div[2]/span[1]", "xpath");
		reference.fetchText("//*[@id='markerInfoClose']/../../div[2]/span[2]", "xpath");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@And("^Validate below components present in the search panel$")
	public void verifySearchBoxFunctionality (DataTable dataTable) throws Exception {
		reference.seleniumClick("//*[@id='icon-expand-menu']", "xpath");
		List<List<String>> compMaps = dataTable.asLists(String.class);
		System.out.println(compMaps.size());
		for (int itr=0; itr<compMaps.size(); itr++) {
			try {
				String property = "//*[@id='"+compMaps.get(0).get(itr)+"' or span[contains(text(),'"+compMaps.get(0).get(itr)+"')] or @class='"+compMaps.get(0).get(itr)+"']";
				reference.presenceOfElement(property, "xpath");
				} catch (Exception e) {
					System.out.println(compMaps.get(0).get(itr)+" Not found in the UI.");
				}
		}
	}
	@Then("^Close the browser$")
	public void closebrowser () {
		reference.close();
	}
}
