package com.ChromeEX.Chrome.Extenstion.API.controller;

import com.ChromeEX.Chrome.Extenstion.API.service.Implement.demoServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class DemoController {
    private final demoServiceImpl demoService;

    @GetMapping("/bit")
    public String getBalance() throws JSONException {
        return demoService.getBalance();
    }

}
