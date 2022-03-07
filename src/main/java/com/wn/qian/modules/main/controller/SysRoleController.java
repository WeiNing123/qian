package com.wn.qian.modules.main.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wn.qian.framework.base.BaseController;
import com.wn.qian.modules.main.model.RoleMenu;
import com.wn.qian.modules.main.model.RoleTree;
import com.wn.qian.modules.main.model.SysRole;
import com.wn.qian.modules.main.service.IRoleMenuService;
import com.wn.qian.modules.main.service.IRoleTreeService;
import com.wn.qian.modules.main.service.ISysRoleService;
import com.wn.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/main/role")
public class SysRoleController extends BaseController {

    @Autowired
    private ISysRoleService sysRoleService;
    @Autowired
    private IRoleTreeService roleTreeService;
    @Autowired
    private IRoleMenuService roleMenuService;

    @PostMapping("/list")
    public IPage<SysRole> list(@RequestBody SysRole sysRole) {
        Page<SysRole> page = new Page<>(sysRole.getPage(), sysRole.getLimit());
        return sysRoleService.page(page, sysRoleService.getQueryWrapper(sysRole));
    }

    @PostMapping("/add")
    public R add(@RequestBody SysRole sysRole) {
        sysRoleService.save(sysRole);
        return R.ok();
    }

    @PostMapping("/edit")
    public R edit(@RequestBody SysRole sysRole) {
        sysRoleService.updateById(sysRole);
        return R.ok();
    }

    @PostMapping("/delete")
    public R delete(@RequestBody SysRole sysRole) {
        sysRoleService.removeById(sysRole.getId());
        roleMenuService.removeRole(sysRole.getId());
        roleTreeService.removeRole(sysRole.getId());
        return R.ok();
    }

    @PostMapping("/saveMenu")
    public R saveMenu(@RequestBody SysRole sysRole) {
        roleTreeService.removeRole(sysRole.getId());
        for (Integer i : sysRole.getTreeIds()) {
            RoleTree tree = new RoleTree();
            tree.setRoleId(sysRole.getId());
            tree.setMenuId(i);
            roleTreeService.save(tree);
        }
        roleMenuService.removeRole(sysRole.getId());
        for (Integer i : sysRole.getMenuIds()) {
            RoleMenu menu = new RoleMenu();
            menu.setRoleId(sysRole.getId());
            menu.setMenuId(i);
            roleMenuService.save(menu);
        }
        return R.ok();
    }

    @PostMapping("/loadTree")
    public R loadTree(@RequestBody RoleTree roleTree) {
        List<RoleTree> list = roleTreeService.list(roleTreeService.getQueryWrapper(roleTree));
        List<Integer> result = new ArrayList<>();
        for (RoleTree tree : list) {
            result.add(tree.getMenuId());
        }
        return R.ok().put("data", result);
    }
}
