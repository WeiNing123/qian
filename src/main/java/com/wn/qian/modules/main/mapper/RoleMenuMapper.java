package com.wn.qian.modules.main.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wn.qian.modules.main.model.RoleMenu;

public interface RoleMenuMapper extends BaseMapper<RoleMenu> {
    int removeRole(Integer roleId);
}
