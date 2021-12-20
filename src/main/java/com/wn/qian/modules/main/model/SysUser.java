package com.wn.qian.modules.main.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.wn.qian.framework.base.BaseModel;
import lombok.Data;

import java.util.Date;

@TableName("sys_user")
@Data
public class SysUser extends BaseModel {

    //用户名
    private String userCode;

    //姓名
    private String userName;

    //密码
    private String password;

    //部门id
    private Integer departmentId;

    //联系方式
    private String phone;

    //性别
    private Integer sex;

    //邮箱
    private String mail;

    //添加时间
    private Date addTime;

    //用户类型
    private String type;
}
