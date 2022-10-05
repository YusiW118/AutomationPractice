package automation.glue;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;

import automation.config.RunFrameworkConfiguration;
import automation.driver.DriverSingleton;
import automation.pages.HomePage;
import automation.pages.SignInPage;
import automation.utilities.ConfigurationProperties;
import automation.utilities.Constants;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

@ContextConfiguration(classes=RunFrameworkConfiguration.class)
public class StepDefinition{
    private WebDriver driver;
    private HomePage home;
    private SignInPage signIn;



    /* using autowired we can access the properties without declarating them */
    @Autowired
    ConfigurationProperties configurationProperties;

    @Before
    public void initializedObject(){
        DriverSingleton.getInstance(configurationProperties.getBrowser());
        home= new HomePage();
        signIn = new SignInPage();

    }

    @Given("I go to the Website")
    public void i_go_to_the_Website(){
        driver=DriverSingleton.getDriver();
        driver.get(Constants.URL);

    }

    @When("I click on the sign in bottom")
    public void i_click_on_the_sign_in_bottom(){
        home.clickSignIn();
    }

    @And("I specify my credentials and click login")
    public void i_specify_my_credentials_and_click_login() throws IOException{
        signIn.login(configurationProperties.getEmail(), configurationProperties.getPassword());
    }

    @Then("I can log into the website")
    public void i_can_log_into_the_website(){
        assertEquals(configurationProperties.getUsername3(), home.getUserName());
    }



}
