package com.wn.qian.modules.main.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wn.qian.modules.main.mapper.SysRoleMapper;
import com.wn.qian.modules.main.model.SysRole;
import com.wn.qian.modules.main.service.ISysRoleService;
import org.springframework.stereotype.Service;

@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService{

    @Override
    public QueryWrapper<SysRole> getQueryWrapper(SysRole sysRole) {
        QueryWrapper<SysRole> wrapper = new QueryWrapper<>();
        if (sysRole.getId() != null) wrapper.eq("id", sysRole.getId());
        wrapper.orderByAsc("id");
        return wrapper;
    }
}
