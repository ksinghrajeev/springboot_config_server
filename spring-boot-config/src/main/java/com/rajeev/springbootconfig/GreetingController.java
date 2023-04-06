package com.rajeev.springbootconfig;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author rajeevkumar
 */
@RestController
@RefreshScope
public class GreetingController {

    @Value("${my.greeting: default value}")
    private String greetingMessage;

    @Value("${my.list.values}")
    private List<String> listValues;

    //@Value("#{${dbValues}}")
    //private Map<String, String> dbValues;
    
    @Autowired
    private DbSettings dbSettings;

    @Autowired
    private Environment env;

    public GreetingController() {

    }

    @GetMapping("/greeting")
    public String greeting() {
        return "Greeting!!! " + greetingMessage + " DB Connection: " + dbSettings.getConnection()
                + " DB Host: " + dbSettings.getHost()
                + " DB Port: " + dbSettings.getPort();
    }

    @GetMapping("/envdetails")
    public String envDetails() {
        return env.toString();
    }
}
