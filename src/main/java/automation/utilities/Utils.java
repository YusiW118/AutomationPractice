package automation.utilities;

import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.Random;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.springframework.util.FileCopyUtils;

import automation.driver.DriverSingleton;

public class Utils {
    public static int testCount=0;
    public static String decode64(String encodedStr){
        Base64.Decoder decoder= Base64.getDecoder();
        return new String(decoder.decode(encodedStr.getBytes()));
    }

    public static boolean takeScreenshot(){
        File file=((TakesScreenshot) DriverSingleton.getDriver()).getScreenshotAs(OutputType.FILE);
        try {
            FileCopyUtils.copy(file, new File(Constants.STRING_FOLDER+ generateRandomString(Constants.LENGHT)+Constants.EXTENSION));
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static String generateRandomString(int length){
        String seedChars="ABCDEFGHIJKLMNÑOPQRSTUVWXYZ";
        StringBuilder sb=new StringBuilder();
        int i=0;
        Random random =new Random();
        while(i<length){
            sb.append(seedChars.charAt(random.nextInt(seedChars.length())));
            i++;
        }
        return sb.toString();
    }
}
