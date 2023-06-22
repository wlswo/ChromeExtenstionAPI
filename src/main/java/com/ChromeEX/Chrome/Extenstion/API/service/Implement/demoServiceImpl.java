package com.ChromeEX.Chrome.Extenstion.API.service.Implement;

import com.ChromeEX.Chrome.Extenstion.API.service.Interface.demoService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class demoServiceImpl implements demoService {

    @Value("#{'${access-key}'.split(';')}")
    private List<String> accessKey;

    @Value("#{'${secret-key}'.split(';')}")
    private List<String> secretKey;

    private final String serverUrl = "https://api.upbit.com";

    @Override
    public String getBalance() throws JSONException {
        JSONArray combinedArray = new JSONArray();
        for(int i=0; i<accessKey.size(); i++) {
            JSONArray jsonArray = new JSONArray(requestUseUpbitAPI(accessKey.get(i),secretKey.get(i)));
            combinedArray.put(jsonArray);
        }
        return combinedArray.toString();
    }

    @Override
    public String requestUseUpbitAPI(String accessK, String secretK) {
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
            return EntityUtils.toString(entity, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "에러요";
    }
}
