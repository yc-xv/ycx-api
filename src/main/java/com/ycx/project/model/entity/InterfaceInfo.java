package com.ycx.project.model.entity;

import io.swagger.annotations.ApiModelProperty;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
* 接口信息
* @TableName interface_info
*/
public class InterfaceInfo implements Serializable {

    /**
    * 主键
    */

    @ApiModelProperty("主键")
    public Long id;
    /**
    * 名称
    */
    @NotBlank(message="[名称]不能为空")
    @Size(max= 256,message="编码长度不能超过256")
    @ApiModelProperty("名称")

    public String name;
    /**
    * 描述
    */
    @Size(max= 256,message="编码长度不能超过256")
    @ApiModelProperty("描述")

    public String description;
    /**
    * 接口地址
    */
    @NotBlank(message="[接口地址]不能为空")
    @Size(max= 512,message="编码长度不能超过512")
    @ApiModelProperty("接口地址")

    public String url;
    /**
    * 请求参数
    */
    @NotBlank(message="[请求参数]不能为空")
    @Size(max= -1,message="编码长度不能超过-1")
    @ApiModelProperty("请求参数")

    public String requestParams;
    /**
    * 请求头
    */
    @Size(max= -1,message="编码长度不能超过-1")
    @ApiModelProperty("请求头")

    public String requestHeader;
    /**
    * 响应头
    */
    @Size(max= -1,message="编码长度不能超过-1")
    @ApiModelProperty("响应头")

    public String responseHeader;
    /**
    * 接口状态（0-关闭，1-开启）
    */

    @ApiModelProperty("接口状态（0-关闭，1-开启）")
    public Integer status;
    /**
    * 请求类型
    */
    @NotBlank(message="[请求类型]不能为空")
    @Size(max= 256,message="编码长度不能超过256")
    @ApiModelProperty("请求类型")

    public String method;
    /**
    * 创建人
    */

    @ApiModelProperty("创建人")
    public Long userId;
    /**
    * 创建时间
    */

    @ApiModelProperty("创建时间")
    public Date createTime;
    /**
    * 更新时间
    */

    @ApiModelProperty("更新时间")
    public Date updateTime;
    /**
    * 是否删除(0-未删, 1-已删)
    */

    @ApiModelProperty("是否删除(0-未删, 1-已删)")
    public Integer isDelete;

    /**
    * 主键
    */
    public void setId(Long id){
    this.id = id;
    }

    /**
    * 名称
    */
    public void setName(String name){
    this.name = name;
    }

    /**
    * 描述
    */
    public void setDescription(String description){
    this.description = description;
    }

    /**
    * 接口地址
    */
    public void setUrl(String url){
    this.url = url;
    }

    /**
    * 请求参数
    */
    public void setRequestParams(String requestParams){
    this.requestParams = requestParams;
    }

    /**
    * 请求头
    */
    public void setRequestHeader(String requestHeader){
    this.requestHeader = requestHeader;
    }

    /**
    * 响应头
    */
    public void setResponseHeader(String responseHeader){
    this.responseHeader = responseHeader;
    }

    /**
    * 接口状态（0-关闭，1-开启）
    */
    public void setStatus(Integer status){
    this.status = status;
    }

    /**
    * 请求类型
    */
    public void setMethod(String method){
    this.method = method;
    }

    /**
    * 创建人
    */
    public void setUserId(Long userId){
    this.userId = userId;
    }

    /**
    * 创建时间
    */
    public void setCreateTime(Date createTime){
    this.createTime = createTime;
    }

    /**
    * 更新时间
    */
    public void setUpdateTime(Date updateTime){
    this.updateTime = updateTime;
    }

    /**
    * 是否删除(0-未删, 1-已删)
    */
    public void setIsDelete(Integer isDelete){
    this.isDelete = isDelete;
    }


    /**
    * 主键
    */
    public Long getId(){
    return this.id;
    }

    /**
    * 名称
    */
    public String getName(){
    return this.name;
    }

    /**
    * 描述
    */
    public String getDescription(){
    return this.description;
    }

    /**
    * 接口地址
    */
    public String getUrl(){
    return this.url;
    }

    /**
    * 请求参数
    */
    public String getRequestParams(){
    return this.requestParams;
    }

    /**
    * 请求头
    */
    public String getRequestHeader(){
    return this.requestHeader;
    }

    /**
    * 响应头
    */
    public String getResponseHeader(){
    return this.responseHeader;
    }

    /**
    * 接口状态（0-关闭，1-开启）
    */
    public Integer getStatus(){
    return this.status;
    }

    /**
    * 请求类型
    */
    public String getMethod(){
    return this.method;
    }

    /**
    * 创建人
    */
    public Long getUserId(){
    return this.userId;
    }

    /**
    * 创建时间
    */
    public Date getCreateTime(){
    return this.createTime;
    }

    /**
    * 更新时间
    */
    public Date getUpdateTime(){
    return this.updateTime;
    }

    /**
    * 是否删除(0-未删, 1-已删)
    */
    public Integer getIsDelete(){
    return this.isDelete;
    }

}
