package com.wn.qian.modules.main.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.wn.qian.framework.base.BaseModel;
import lombok.Data;

@TableName("password_management")
@Data
public class PasswordManagement extends BaseModel {

    //名称
    private String label;
    //用户名
    private String account;
    //密码
    private String password;
    //备注
    private String remark;
    //创建人
    private String creater;
}
