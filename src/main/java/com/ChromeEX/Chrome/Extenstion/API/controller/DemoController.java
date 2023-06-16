package com.ChromeEX.Chrome.Extenstion.API.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ChromeEX.Chrome.Extenstion.API.service.Implement.demoServiceImpl;

@RequiredArgsConstructor
@RestController
public class DemoController {
    private final demoServiceImpl demoService;

    @GetMapping("/")
    public String getBalance() {
        return demoService.getBalance();
    }

}
