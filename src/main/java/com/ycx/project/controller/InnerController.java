package com.ycx.project.controller;

import com.ycx.interfaces.InnerInterfaceInfoService;
import com.ycx.interfaces.InnerUserInterfaceInfoService;
import com.ycx.interfaces.InnerUserService;
import com.ycx.model.entity.InterfaceInfo;

import com.ycx.model.entity.User;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/inner")
public class InnerController {
    @Resource
    private InnerUserService innerUserService;
    @Resource
    private InnerInterfaceInfoService innerInterfaceInfoService;
    @Resource
    private InnerUserInterfaceInfoService innerUserInterfaceInfoService;

    @GetMapping("/user")
    public User getUser(String accessKey) {
        return innerUserService.getInvokeUser(accessKey);
    }
    @GetMapping("/interface")
    public InterfaceInfo getInterface(String url, String method) {
        return innerInterfaceInfoService.getInterfaceInfo(url, method);
    }
    @PostMapping("/leftNum")
    public boolean invokeLeftNum(@RequestParam("interfaceInfoId") long interfaceInfoId,@RequestParam("userId") long userId) {
        return innerUserInterfaceInfoService.invokeLeftNum(interfaceInfoId, userId);
    }
    @PostMapping("/count")
    public boolean invokeCount(@RequestParam("interfaceInfoId") long interfaceInfoId,@RequestParam("userId") long userId) {
        return innerUserInterfaceInfoService.invokeCount(interfaceInfoId, userId);
    }
}
