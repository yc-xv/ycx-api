package com.ycx.ycxinterface.controller;


import com.ycx.ycxapisdk.model.User;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@RestController
@RequestMapping("/name")
public class NameController {

    @GetMapping("/")
    public String getNameByGet(String name) {
        return "GET 你的名字是" + name;
    }

    @PostMapping("/")
    public String getNameByPost(@RequestParam String name) {
        return "POST 你的名字是" + name;
    }

    @PostMapping("/user")
    public String getUserNameByPost(@RequestBody User user, HttpServletRequest request) {
        String accesskey = request.getHeader("accessKey");
        String sign = request.getHeader("sign");
        String timestamp = request.getHeader("timestamp");
        String nonce = request.getHeader("nonce");
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("accessKey", accesskey);
        paramMap.put("sign", sign);
        paramMap.put("timestamp", timestamp);
        paramMap.put("nonce", nonce);
        return "POST 用户名字是" + user.getUsername()+paramMap.toString();
    }


}
