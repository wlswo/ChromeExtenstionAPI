package com.ChromeEX.Chrome.Extenstion.API.service.Implement;

import com.ChromeEX.Chrome.Extenstion.API.service.Interface.demoService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class demoServiceImpl implements demoService {

    @Override
    public Map<String, Object> demoService() {
        Map<String, Object> data = new HashMap<>();
        data.put("1","check1");
        data.put("2","check1");
        data.put("3","check1");

        return data;
    }
}
