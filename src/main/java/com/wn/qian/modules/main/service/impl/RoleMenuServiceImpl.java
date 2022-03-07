package com.wn.qian.modules.main.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wn.qian.modules.main.mapper.RoleMenuMapper;
import com.wn.qian.modules.main.model.RoleMenu;
import com.wn.qian.modules.main.service.IRoleMenuService;
import org.springframework.stereotype.Service;

@Service
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenu> implements IRoleMenuService {
    @Override
    public QueryWrapper<RoleMenu> getQueryWrapper(RoleMenu roleMenu) {
        QueryWrapper<RoleMenu> wrapper = new QueryWrapper<>();
        if (roleMenu.getRoleId() != null) wrapper.eq("role_id", roleMenu.getRoleId());
        wrapper.orderByAsc("menu_id");
        return wrapper;
    }

    @Override
    public int removeRole(Integer roleId) {
        return baseMapper.removeRole(roleId);
    }
}
