package com.wn.qian.modules.main.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wn.qian.framework.base.BaseModel;
import lombok.Data;

import java.util.Date;
import java.util.List;

@TableName("sys_menu")
@Data
public class SysMenu extends BaseModel {

    //名称
    private String label;

    //排序
    private Integer orderBy;

    //父id
    private Integer parentId;

    //路径
    private String path;

    //图标
    private String icon;

    @TableField(exist = false)
    private List<SysMenu> children;
}
