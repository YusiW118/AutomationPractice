import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.io.IOException;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebDriver;

import driver.DriverSingleton;
import pages.CheckOutPage;
import pages.HomePage;
import pages.SignInPage;
import utilities.Constants;
import utilities.FrameworkProperties;
/* The testcases will be run according to their name */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Tests {
    static FrameworkProperties frameworkProperties;
    static WebDriver driver;
    static HomePage homePage;
    static SignInPage signInPage;
    static CheckOutPage checkOutPage;
    
    @Before
    public void initializedObject() throws IOException{
        frameworkProperties = new FrameworkProperties();
        DriverSingleton.getInstance(frameworkProperties.getProperty(Constants.BROWSER));
        driver = DriverSingleton.getDriver();
        homePage= new HomePage();
        signInPage= new SignInPage();
        checkOutPage=new CheckOutPage();
    }


    @Test
    public void test01Authentication() throws IOException{
        driver.get(Constants.URL);
        homePage.clickSignIn();
        signInPage.login(frameworkProperties.getProperty(Constants.EMAIL), frameworkProperties.getProperty(Constants.PASSWORD));
        assertEquals(frameworkProperties.getProperty(Constants.USERNAME), homePage.getUserName());
    }

    @Test
    public void test02AddingThingsToCart(){
        driver.get(Constants.URL);
        homePage.addFirstElement();
        homePage.addSecondElement();
        assertEquals(Constants.CART_QUANTITY_TEST, checkOutPage.getSummerProducts());
        
    }

    @Test
    public void test03TheFullBuyingProgress() throws IOException{
        driver.get(Constants.URL);
        homePage.addFirstElement();
        homePage.addSecondElement();

        checkOutPage.goToCheckout();
        signInPage.login(frameworkProperties.getProperty(Constants.EMAIL), frameworkProperties.getProperty(Constants.PASSWORD));
        checkOutPage.confirmAddress();
        checkOutPage.confirmShipping();
        checkOutPage.payBankWire();
        checkOutPage.confirmPaid();
        assertEquals(true, checkOutPage.checkFinalStatus());  
    }

    @AfterClass
    public static void closeObject(){
        driver.close();
    }

}
