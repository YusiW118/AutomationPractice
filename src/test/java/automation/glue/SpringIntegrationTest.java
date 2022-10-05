package automation.glue;

import org.springframework.boot.test.context.SpringBootTest;

import automation.config.RunFrameworkConfiguration;
import io.cucumber.spring.CucumberContextConfiguration;

@CucumberContextConfiguration
@SpringBootTest(classes =RunFrameworkConfiguration.class )
public class SpringIntegrationTest {
    
}
