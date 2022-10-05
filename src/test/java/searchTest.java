import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;

import automation.driver.DriverSingleton;
import automation.pages.CheckOutPage;
import automation.pages.HomePage;
import automation.pages.SignInPage;
import automation.utilities.Constants;
import automation.utilities.FrameworkProperties;


@RunWith(Parameterized.class)
public class searchTest {
    static FrameworkProperties frameworkProperties;
    static WebDriver driver;
    static HomePage homePage;

    String inputString;
    Boolean expectedResult;


    @Before
    public void initializedObject() throws IOException{
        frameworkProperties = new FrameworkProperties();
        DriverSingleton.getInstance(frameworkProperties.getProperty(Constants.BROWSER));
        driver = DriverSingleton.getDriver();
        homePage= new HomePage();
    }

    public searchTest(String inputStrings, Boolean expectedResults){
        this.inputString=inputStrings;
        this.expectedResult=expectedResults;
    }

    @Parameterized.Parameters
    public static Collection searchOption(){
        return Arrays.asList(new Object[][] {
            {"Shirt", true},
            {"Blouse", true},
            {"Dress", true},
            {"", false},
            {"test", false}
        });
        
    }

    @Test
    public void testingSearch(){
        driver.get(Constants.URL);
        assertEquals(expectedResult, homePage.searchElement(inputString));
    }

    @AfterClass
    public static void closeObject(){
        driver.close();
    }
}
