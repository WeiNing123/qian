package com.wn.qian.modules.main.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wn.qian.framework.base.BaseImpl;
import com.wn.qian.modules.main.model.RoleMenu;

public interface IRoleMenuService extends IService<RoleMenu>, BaseImpl<RoleMenu> {
    int removeRole(Integer roleId);
}
