package runners;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
    features = "src/test/resources/features",
    glue =  "stepdefinitions",
    tags = "@instalacion",
    plugin = {"pretty", "html:target/cucumber-reports.html"},
    snippets = CucumberOptions.SnippetType.CAMELCASE

)
public class InstalacionRunner {}
