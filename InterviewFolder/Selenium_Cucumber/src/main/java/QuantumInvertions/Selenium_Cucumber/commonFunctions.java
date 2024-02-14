package QuantumInvertions.Selenium_Cucumber;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class commonFunctions {
	String dir = System.getProperty("user.dir");
	WebDriver driver = null;
	accessConfigData configData = null;
	public void updateDriver(String browserName) throws Exception{
		configData = new accessConfigData();
		String reqMode = configData.getMode();
		switch(browserName){
		case "Edge":
			if(reqMode == "true") {
			EdgeOptions options=new EdgeOptions();
			options.addArguments("--headless");
			System.setProperty(
		            "webdriver.edge.driver",
		            dir+"\\msedgedriver.exe");
			driver = new EdgeDriver(options);
			} else {
				driver = new EdgeDriver();
			}
	        break;
		case "Chrome":
			System.setProperty("webdriver.chrome.driver",dir+"\\chromedriver.exe");
			Runtime.getRuntime().exec("taskkill /F /IM chrome.exe");
			Thread.sleep(3000);
			if(reqMode == "true") {
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--headless");
				driver = new ChromeDriver(options);
			} else {
			driver = new ChromeDriver();
			}
			break;
		case "Firefox":
			Runtime.getRuntime().exec("taskkill /F /IM firefox.exe");
			if(reqMode == "true") {
			FirefoxOptions options = new FirefoxOptions();
			options.addArguments("--headless");
			driver = new FirefoxDriver(options);
			} else {
				driver = new FirefoxDriver();
			}
			break;
		default:
			
			System.setProperty("webdriver.chrome.driver",dir+"\\chromedriver.exe");
			Runtime.getRuntime().exec("taskkill /F /IM chrome.exe");
			Thread.sleep(3000);
			driver = new ChromeDriver();
			
		}

		// Maximize the browser
        driver.manage().window().maximize();
	}
	public void openWebsite(String browser,String website) throws Exception{
		String status="";
		String exception="";
		int maxsleep = 10;
		int intervel_time = 1;
		try {
			while (intervel_time < maxsleep) {
				Thread.sleep(3000);
				try{
					updateDriver(browser);
					driver.get(website);
					Thread.sleep(3000);
					driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
					driver.manage().window().maximize();
					status = "Pass";
					break;
				}
				catch(Exception e){
					System.out.println("exception"+e.getMessage());
				}
			}
		} 
			catch (Exception e) {
			exception = e.getMessage();
		}
	}
	public By convertLocatorToWebElement(String property, String locater) throws Exception {
		By by = null;
		switch (locater) {
		case "id":
			by = By.id(property);
			break;
		case "name":
			by = By.name(property);
			break;
		case "className":
			by = By.className(property);
			break;
		case "xpath":
			by = By.xpath(property);
			break;
		case "css":
			by = By.cssSelector(property);
			break;
		case "linkText":
			by = By.linkText(property);
			break;
		case "PartialLinkText":
			by = By.partialLinkText(property);
			break;
		}
		return by;
	}
	
	public void scrollingFunction(String property, String locater) throws Exception {
		String status = "";
		String exception = "";
		By by = convertLocatorToWebElement(property, locater);
		double maxsleep = 8;
		int intervel_time = 2;
		try {
			while (intervel_time < maxsleep) {
				Thread.sleep(2000);
				try {
					if (driver.findElements(by).size() > 0) {
						WebElement element = driver.findElement(by);
						JavascriptExecutor js = (JavascriptExecutor)driver;
						js.executeScript("arguments[0].scrollIntoView();", element);
						status = "Pass";
						break;
					} else {
						status = "Fail";
					}

				} catch (Exception e) {
					status = "Fail";
				}
			}
		} catch (Exception e) {
			exception = e.getMessage();
		}
	}
	public void seleniumClick(String property, String locater) throws Exception {
		By by = convertLocatorToWebElement(property, locater);
		WebElement element = driver.findElement(by);
		element.click();
	}
	public void javascriptClick (String property, String locater) throws Exception {
		By by = convertLocatorToWebElement(property, locater);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", driver.findElement(by));
	}
	public void typeText (String property, String locater, String text) throws Exception {
		By by = convertLocatorToWebElement(property, locater);
		driver.findElement(by).sendKeys(text);
	}
	public void presenceOfElement (String property, String locater) throws Exception {
		By by = convertLocatorToWebElement(property, locater);
		if (driver.findElement(by).isDisplayed()) {
			System.out.println(property+" displayed correctly.");
		} else {
			System.out.println(property+" not displayed correctly.");
		}
	}
	public void fetchText (String property, String locater) throws Exception {
		By by = convertLocatorToWebElement(property, locater);
		System.out.println("Grabbed Text: "+driver.findElement(by).getText());
	}
	public void close () {
		driver.close();
		driver.quit();
	}
	
	public static void main (String args[]) throws Exception {
		commonFunctions reference = new commonFunctions();
		reference.openWebsite("Chrome", "https://www.onemap.gov.sg/");
		Thread.sleep(15000);
		reference.javascriptClick("//*[@id='Community']", "xpath");
	}
}
