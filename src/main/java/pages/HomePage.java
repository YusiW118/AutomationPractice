package pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import driver.DriverSingleton;
import utilities.Constants;
import utilities.Utils;

public class HomePage {
    private WebDriver driver;

    public HomePage(){
        driver=DriverSingleton.getDriver();
        PageFactory.initElements(driver, this);
    }
    

    @FindBy(css="#homefeatured > li.ajax_block_product.col-xs-12.col-sm-4.col-md-3.first-in-line.first-item-of-tablet-line.first-item-of-mobile-line > div > div.right-block > div.button-container > a.button.ajax_add_to_cart_button.btn.btn-default")
    private WebElement addCart1;
    
    @FindBy(css="#homefeatured > li:nth-child(2) > div > div.right-block > div.button-container > a.button.ajax_add_to_cart_button.btn.btn-default")
    private WebElement addCart2;

    @FindBy(css="#header > div:nth-child(3) > div > div > div:nth-child(3) > div > a")
    private WebElement cart;

    @FindBy(css="#layer_cart > div.clearfix > div.layer_cart_cart.col-xs-12.col-md-6 > div.button-container > span > span")
    private WebElement continueShopping;
    @FindBy(css="#layer_cart > div.clearfix > div.layer_cart_cart.col-xs-12.col-md-6 > div.button-container > a")
    private WebElement checkout;

    @FindBy(css="#homefeatured > li.ajax_block_product.col-xs-12.col-sm-4.col-md-3.first-in-line.first-item-of-tablet-line.first-item-of-mobile-line")
    private WebElement firstElement;

    @FindBy(css="#homefeatured > li:nth-child(2)")
    private WebElement secondElement;
    @FindBy(css="#header > div.nav > div > div > nav > div.header_user_info > a")
    private WebElement signButton;

    @FindBy(css="#header > div.nav > div > div > nav > div:nth-child(1) > a > span")
    private WebElement username;

    @FindBy(id="search_query_top")
    private WebElement searchBar;

    @FindBy(css="#searchbox > button")
    private WebElement searchButton;

    @FindBy(css="#center_column > ul > li > div > div.left-block > div > a.product_img_link > img")
    private WebElement searchResult;

    public Boolean searchElement(String elements){
        searchBar.sendKeys(elements);
        searchButton.click();
        try{
            if(searchResult.isEnabled())
            return true;

        }catch(Exception e){
            return false;
        }
        return false;
    }

    public void clickSignIn(){
        WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(Constants.TimeOut));
        wait.until(ExpectedConditions.elementToBeClickable(signButton));
        signButton.click();
    }

    public String getUserName(){
        return username.getText();
    }


    public void addFirstElement(){
        Actions hover = new Actions(driver);
        hover.moveToElement(firstElement).build().perform();
        addCart1.click();
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(Constants.TimeOut));
        wait.until(ExpectedConditions.elementToBeClickable(continueShopping));
        continueShopping.click();
        if(cart.getText().contains(Constants.CART_QUANTITY)){
            System.out.println("Cart has been added");
        }else{
            System.out.println("Cart has not been added");
            Utils.takeScreenshot();
        }
        
    }


    public void addSecondElement(){
        Actions hover = new Actions(driver);
        hover.moveToElement(secondElement).build().perform();
        addCart2.click();
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(Constants.TimeOut));
        wait.until(ExpectedConditions.elementToBeClickable(checkout));
        checkout.click();
    }
}
