package com.ChromeEX.Chrome.Extenstion.API.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ChromeEX.Chrome.Extenstion.API.service.Implement.demoServiceImpl;

import java.util.Map;

@RequiredArgsConstructor
@RestController
public class DemoController {
    private final demoServiceImpl demoService;

    @GetMapping("/demo")
    public Map<String, Object> demoController() {
        return demoService.demoService();
    }
}
