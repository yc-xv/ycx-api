package com.ycx.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ycx.model.entity.InterfaceInfo;

/**
* @author ycx
* @description 针对表【interface_info(接口信息)】的数据库操作Service
* @createDate 2025-12-29 20:57:48
*/
public interface InterfaceInfoService extends IService<InterfaceInfo> {

    void validInterfaceInfo(InterfaceInfo interfaceInfo, boolean add);
}
