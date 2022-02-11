package com.wn.qian.modules.main.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wn.qian.modules.main.mapper.SysMenuMapper;
import com.wn.qian.modules.main.model.SysMenu;
import com.wn.qian.modules.main.service.ISysMenuService;
import org.springframework.stereotype.Service;

@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements ISysMenuService {
    @Override
    public QueryWrapper<SysMenu> getQueryWrapper(SysMenu menu) {
        QueryWrapper<SysMenu> wrapper = new QueryWrapper<>();
        if(menu.getParentId() != null) wrapper.eq("parent_id", menu.getParentId());
        wrapper.orderByAsc("parent_id", "order_by");
        return wrapper;
    }
}
