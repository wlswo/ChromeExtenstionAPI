package com.ChromeEX.Chrome.Extenstion.API.openApiTest;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.Getter;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@SpringBootTest
@Configuration
@PropertySources({
        @PropertySource(name = "app", value = "classpath:application.properties", encoding = "UTF-8")
})
@Getter
public class openApiTest implements EnvironmentAware {

    @Override
    public void setEnvironment(Environment env) {
    }

    @Value("#{'${access-key}'.split(';')}")
    private List<String> accessKey;

    @Value("#{'${secret-key}'.split(';')}")
    private List<String> secretKey;

    private final String serverUrl = "https://api.upbit.com";

    @Test
    void 자산_조회() {
        for(int i=0; i<accessKey.size(); i++) {
            API_호출(accessKey.get(i),secretKey.get(i));
        }
    }

    void API_호출(String accessK, String secretK) {
        Algorithm algorithm = Algorithm.HMAC256(secretK);
        String jwtToken = JWT.create()
                .withClaim("access_key", accessK)
                .withClaim("nonce", UUID.randomUUID().toString())
                .sign(algorithm);

        String authenticationToken = "Bearer " + jwtToken;

        try {
            HttpClient client = HttpClientBuilder.create().build();
            HttpGet request = new HttpGet(serverUrl + "/v1/accounts");
            request.setHeader("Content-Type", "application/json");
            request.addHeader("Authorization", authenticationToken);

            HttpResponse response = client.execute(request);
            HttpEntity entity = response.getEntity();

            System.out.println(EntityUtils.toString(entity, "UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

