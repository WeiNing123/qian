package com.wn.qian.modules.main.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.wn.qian.framework.base.BaseModel;
import lombok.Data;

@TableName("sys_role_menu")
@Data
public class RoleMenu extends BaseModel {

    //角色id
    private Integer roleId;
    //菜单id
    private Integer menuId;
}
