package automation.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("automation")
public class RunFrameworkConfiguration {
    
    public RunFrameworkConfiguration(){
    }
}
