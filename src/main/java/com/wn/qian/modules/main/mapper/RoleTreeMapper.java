package com.wn.qian.modules.main.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wn.qian.modules.main.model.RoleTree;

public interface RoleTreeMapper extends BaseMapper<RoleTree> {
    int removeRole(Integer roleId);
}
