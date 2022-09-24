package pages;

import java.io.IOException;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import driver.DriverSingleton;
import utilities.Constants;
import utilities.FrameworkProperties;
import utilities.Utils;

public class SignInPage{

    private WebDriver driver;
    static FrameworkProperties frameworkProperties;
    

    public SignInPage(){
        this.driver=DriverSingleton.getDriver();
        PageFactory.initElements(driver, this);
        frameworkProperties = new FrameworkProperties();

    }

    @FindBy(id="email")
    private WebElement emailField;

    @FindBy(id="passwd")
    private WebElement password;

    @FindBy(id="SubmitLogin")
    private WebElement submit;

    @FindBy(id="email_create")
    private WebElement emailAdress;

    @FindBy(id="SubmitCreate")
    private WebElement SubmitCreate;


    public void login(String email, String passwords) throws IOException{
        emailField.sendKeys(email);
        password.sendKeys(frameworkProperties.getProperty(Constants.PASSWORD));
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("window.scrollBy(0,250)", "");
        submit.click();

    }

    public void SignUp(String email){
        emailAdress.sendKeys(email);
        SubmitCreate.click();
    }
}