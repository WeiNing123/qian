package com.wn.qian.modules.main.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wn.qian.framework.base.BaseModel;
import lombok.Data;

@TableName("sys_role")
@Data
public class SysRole extends BaseModel {

    //角色名称
    private String label;
    @TableField(exist = false)
    private int[] menuIds;
    @TableField(exist = false)
    private int[] treeIds;
}
