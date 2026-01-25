package com.ycx.project.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ycx.model.entity.UserInterfaceInfo;
import com.ycx.project.service.UserInterfaceInfoService;
import com.ycx.project.mapper.UserInterfaceInfoMapper;
import org.springframework.stereotype.Service;

/**
* @author ycx
* @description 针对表【user_interface_info(用户调用接口关系)】的数据库操作Service实现
* @createDate 2026-01-19 12:12:02
*/
@Service
public class UserInterfaceInfoServiceImpl extends ServiceImpl<UserInterfaceInfoMapper, UserInterfaceInfo>
    implements UserInterfaceInfoService{

}




