package com.ycx.interfaces;

import com.ycx.model.entity.User;

public interface InnerUserService {
    User getInvokeUser(String accessKey);
}
