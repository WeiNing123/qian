package com.wn.qian.modules.main.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wn.qian.framework.base.BaseImpl;
import com.wn.qian.modules.main.model.RoleTree;

public interface IRoleTreeService extends IService<RoleTree>, BaseImpl<RoleTree> {
    int removeRole(Integer roleId);
}
