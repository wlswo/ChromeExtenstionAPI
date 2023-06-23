package com.ChromeEX.Chrome.Extenstion.API.service.Interface;

import org.springframework.boot.configurationprocessor.json.JSONException;

public interface demoService {
    String getBalance() throws JSONException;
    String requestUseUpbitAPI(String accessKey, String secretKey);
}
