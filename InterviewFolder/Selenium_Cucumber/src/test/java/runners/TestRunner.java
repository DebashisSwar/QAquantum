package runners;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
	@CucumberOptions(publish = true,
	   features = "src/test/java/featureFiles",
	   glue = "",
	   plugin = { "pretty", "html:target/cucumber-reports" },
	   monochrome = true,
	   tags= "@landingpage"
	   )
public class TestRunner {
	
}
