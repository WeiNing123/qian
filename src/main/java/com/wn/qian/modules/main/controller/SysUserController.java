package com.wn.qian.modules.main.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wn.qian.framework.base.BaseController;
import com.wn.qian.modules.main.model.SysMenu;
import com.wn.qian.modules.main.model.SysUser;
import com.wn.qian.modules.main.service.ISysMenuService;
import com.wn.qian.modules.main.service.ISysUserService;
import com.wn.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/main/user")
public class SysUserController extends BaseController {

    @Autowired
    private ISysUserService sysUserService;
    @Autowired
    private ISysMenuService sysMenuService;

    @PostMapping("/list")
    public R list(@RequestBody SysUser sysUser) {
        List<SysUser> result = sysUserService.list(sysUserService.getQueryWrapper(sysUser));
        return R.ok().put("data", result);
    }

    @GetMapping("/info")
    public R info() {
        String userCode = (String) getRedisUtil().get("user_code");
        SysUser user = new SysUser();
        user.setUserCode(userCode);
        SysUser userInfo = sysUserService.getOne(sysUserService.getQueryWrapper(user));

        List<String>roleList = new ArrayList<String>();
        roleList.add("admin");roleList.add("develop");

        QueryWrapper<SysMenu> wrapper = new QueryWrapper<>();
        wrapper.orderByAsc("parent_id", "order_by");
        List<SysMenu>menu = sysMenuService.list(wrapper);
        return R.ok().put("data", userInfo).put("roles", roleList).put("menu", menu);
    }
}
