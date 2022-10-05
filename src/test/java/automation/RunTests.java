package automation;


import org.junit.runner.RunWith;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;


@RunWith(Cucumber.class)
@CucumberOptions(
    plugin = {"pretty", "html:target/cucumber-reports.html"},
    glue = {"automation/glue"},
    features = "src/main/resources/features"
)
public class RunTests {
}
