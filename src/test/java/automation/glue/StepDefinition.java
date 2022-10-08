package automation.glue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.net.URISyntaxException;

import javax.swing.text.Utilities;

import org.junit.AfterClass;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import automation.config.RunFrameworkConfiguration;
import automation.driver.DriverSingleton;
import automation.pages.CheckOutPage;
import automation.pages.HomePage;
import automation.pages.SignInPage;
import automation.utilities.ConfigurationProperties;
import automation.utilities.Constants;
import automation.utilities.Log;
import automation.utilities.TestCases;
import automation.utilities.Utils;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

@ContextConfiguration(classes=RunFrameworkConfiguration.class)
public class StepDefinition{
    private static WebDriver driver;
    private HomePage home;
    private SignInPage signIn;
    private CheckOutPage checkOutPage;
    static ExtentReports report; //https://www.extentreports.com/docs/versions/5/java/index.html
    static ExtentSparkReporter htmlReporter;
    ExtentTest test;



    /* using autowired we can access the properties without declarating them */
    @Autowired
    ConfigurationProperties configurationProperties;
    @BeforeAll
    public static void initReport(){
        report=new ExtentReports();
        htmlReporter=new ExtentSparkReporter("report/TestReport.html");
        
    }

    @Before
    public void initializedObject() throws URISyntaxException{
        DriverSingleton.getInstance(configurationProperties.getBrowser());
       
        report.attachReporter(htmlReporter);
        home= new HomePage();
        signIn = new SignInPage();
        checkOutPage= new CheckOutPage();
        TestCases[] tests=TestCases.values();
        
        test=report.createTest(tests[Utils.testCount].getTestName());
        Log.getLogData(Log.class.getName());
        Log.startTest(tests[Utils.testCount].getTestName());
        Utils.testCount++;
    }

    @Given("I go to the Website")
    public void i_go_to_the_Website(){
        driver=DriverSingleton.getDriver();
        driver.get(Constants.URL);
        Log.info("Navigating to "+ Constants.URL);
        test.log(Status.PASS, "Navigation to the Website");

    }

    @When("I click on the sign in bottom")
    public void i_click_on_the_sign_in_bottom(){
        home.clickSignIn();
        test.log(Status.PASS, "click on the bottom");
    }
    @When("I add two elements to the cart")
    public void i_add_two_elements_to_the_cart(){
        home.addFirstElement();
        home.addSecondElement();
        test.log(Status.PASS, "Adding two elements");
    }

    @And("I specify my credentials and click login")
    public void i_specify_my_credentials_and_click_login() throws IOException{
        signIn.login(configurationProperties.getEmail(), configurationProperties.getPassword());
        test.log(Status.PASS, "credentials information to login");
    }

    @And("I proceed the checkout")
    public void i_proceed_the_checkout(){
        checkOutPage.goToCheckout();
        test.log(Status.PASS, "checkout");
    }
    @And("I confirm address, shipping and final order")
    public void i_confirm_address_shipping_final_order() throws IOException{
        signIn.login(configurationProperties.getEmail(), configurationProperties.getPassword());
        checkOutPage.confirmAddress();
        checkOutPage.confirmShipping();
        checkOutPage.payBankWire();
        checkOutPage.confirmPaid();
        test.log(Status.PASS, "confirmation for the order");

    }

    @Then("I can log into the website")
    public void i_can_log_into_the_website(){
        if (configurationProperties.getUsername3().equals(home.getUserName())){
            test.log(Status.PASS, "authentication is successful");
        }else{
            test.log(Status.FAIL, "authentication is not successful");
        }
        
    }

    @Then("The elements are bought")
    public void the_elements_are_bought(){
        if(checkOutPage.checkFinalStatus()){
            test.log(Status.PASS, "elements are bought");
        }else{
            test.log(Status.FAIL, "something has happened");
        }
    }

    @After
    public static void closeObject(){ 
        DriverSingleton.closeObjectInstance();
        report.flush();
    }


}
