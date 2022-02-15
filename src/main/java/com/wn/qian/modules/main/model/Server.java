package com.wn.qian.modules.main.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.wn.qian.framework.base.BaseModel;
import lombok.Data;

@TableName("server")
@Data
public class Server extends BaseModel {

    //名称
    private String label;
    //ip地址
    private String ip;
    //端口
    private String port;
    //密码
    private String password;
    //备注
    private String remark;
}
