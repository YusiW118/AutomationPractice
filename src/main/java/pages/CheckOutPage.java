package pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import driver.DriverSingleton;
import utilities.Constants;

public class CheckOutPage {
    private WebDriver driver;
    
    
    public CheckOutPage(){
        driver = DriverSingleton.getDriver();
        PageFactory.initElements(driver, this);

    }

    @FindBy(css="#center_column > p.cart_navigation.clearfix > a.button.btn.btn-default.standard-checkout.button-medium > span")
    private WebElement checkout;

    @FindBy(css="head > title")
    private WebElement title;
    
    @FindBy(css="#center_column > form > p > button > span")
    private WebElement checkout2;

    @FindBy(id="cgv")
    private WebElement checkService;

    @FindBy(css="#form > p > button > span")
    private WebElement checkout3;

    @FindBy(css="#HOOK_PAYMENT > div:nth-child(1) > div > p > a")
    private WebElement payByBank;

    @FindBy(css="#cart_navigation > button > span")
    private WebElement payPay;

    @FindBy(css="#center_column > div > p > strong")
    private WebElement TextOrder;

    @FindBy(id="summary_products_quantity")
    private WebElement products;

    public Boolean checkTitle(String titles){
        return title.getText().equals(titles);
    }

    public void goToCheckout(){
        WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(Constants.TimeOut));
        wait.until(ExpectedConditions.elementToBeClickable(checkout));

        checkout.click();
    }

    public void confirmAddress(){
        WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(Constants.TimeOut));
        wait.until(ExpectedConditions.elementToBeClickable(checkout2));

        checkout2.click();
    }
    public void confirmShipping(){
        WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds( Constants.TimeOut));
        wait.until(ExpectedConditions.elementToBeClickable(checkService));
        checkService.click();
        checkout3.click();
    }

    public void payBankWire(){
        WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(Constants.TimeOut));
        wait.until(ExpectedConditions.elementToBeClickable(payByBank));
        payByBank.click();
    }
    public void confirmPaid(){
        WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(Constants.TimeOut));
        wait.until(ExpectedConditions.elementToBeClickable(payPay));
        payPay.click();
    }

    public Boolean checkFinalStatus(){
        WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(Constants.TimeOut));
        wait.until(ExpectedConditions.elementToBeClickable(TextOrder));
        return TextOrder.getText().contains(Constants.COMPLETE_ORDER);
    }

    public String getSummerProducts(){
        return products.getText();
    }
}
