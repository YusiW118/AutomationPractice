package automation.utilities;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;


@PropertySource("classpath:/framework.properties")
@Component
public class ConfigurationProperties {
    
    @Value("${browser}")
    private String browser;

    @Value("${email}")
     private String email;

    @Value("${password}")
     private String password;

    @Value("${username3}")
     private String username3;

    public String getBrowser() {
        System.out.println(this.browser);
        return browser;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    
    public String getUsername3() {
        return username3;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsernam3(String username) {
        this.username3 = username;
    }


}
