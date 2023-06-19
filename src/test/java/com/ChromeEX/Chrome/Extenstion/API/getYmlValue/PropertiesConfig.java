package com.ChromeEX.Chrome.Extenstion.API.getYmlValue;

import lombok.Getter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;

import java.util.List;

@SpringBootTest
@Configuration
@PropertySources({
        @PropertySource(name = "app", value = "classpath:application.properties", encoding = "UTF-8")
})
@Getter
public class PropertiesConfig implements EnvironmentAware {
    @Override
    public void setEnvironment(Environment env) {
    }

    // \ 기호는 propertis에서 줄바꿈 
    @Value("#{'${access-key}'.split(';')}")
    private List<String> accessKey;

    @Value("#{'${secret-key}'.split(';')}")
    private List<String> secretKey;


    @Test
    void getAccessKey() {
        accessKey.forEach(System.out::println);
    }

    @Test
    void getSecretKey() {
        secretKey.forEach(System.out::println);
    }
}