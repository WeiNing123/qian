package com.wn.qian.modules.main.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wn.qian.modules.main.mapper.RoleTreeMapper;
import com.wn.qian.modules.main.model.RoleTree;
import com.wn.qian.modules.main.service.IRoleTreeService;
import org.springframework.stereotype.Service;

@Service
public class RoleTreeServiceImpl extends ServiceImpl<RoleTreeMapper, RoleTree> implements IRoleTreeService {
    @Override
    public QueryWrapper<RoleTree> getQueryWrapper(RoleTree roleTree) {
        QueryWrapper<RoleTree> wrapper = new QueryWrapper<>();
        if (roleTree.getRoleId() != null) wrapper.eq("role_id", roleTree.getRoleId());
        wrapper.orderByAsc("menu_id");
        return wrapper;
    }

    @Override
    public int removeRole(Integer roleId) {
        return baseMapper.removeRole(roleId);
    }
}
