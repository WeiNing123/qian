package com.wn.qian.modules.main.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wn.qian.framework.base.BaseController;
import com.wn.qian.modules.main.model.SysMenu;
import com.wn.qian.modules.main.model.SysUser;
import com.wn.qian.modules.main.service.ISysMenuService;
import com.wn.qian.modules.main.service.ISysUserService;
import com.wn.utils.MD5;
import com.wn.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/main/user")
public class SysUserController extends BaseController {

    @Autowired
    private ISysUserService sysUserService;
    @Autowired
    private ISysMenuService sysMenuService;

    @GetMapping("/info")
    public R info() {
        String userCode = (String) getRedisUtil().get("user_code");
        SysUser user = new SysUser();
        user.setUserCode(userCode);
        SysUser userInfo = sysUserService.getOne(sysUserService.getQueryWrapper(user));

        List<String>roleList = new ArrayList<String>();
        roleList.add("admin");roleList.add("develop");

        QueryWrapper<SysMenu> wrapper = new QueryWrapper<>();
        wrapper.inSql("id", "select menu_id from sys_role_menu where role_id="+userInfo.getRoleId());
        wrapper.orderByAsc("parent_id", "order_by");
        List<SysMenu>menu = sysMenuService.list(wrapper);
        return R.ok().put("data", userInfo).put("roles", roleList).put("menu", menu);
    }

    @PostMapping("/list")
    public IPage<SysUser> list(@RequestBody SysUser sysUser){
        Page<SysUser> page = new Page<>(sysUser.getPage(), sysUser.getLimit());
        return sysUserService.page(page, sysUserService.getQueryWrapper(sysUser));
    }

    @PostMapping("/add")
    public R add(@RequestBody SysUser sysUser) throws Exception{
        SysUser user = new SysUser();
        user.setUserCode(sysUser.getUserCode());
        SysUser info = sysUserService.getOne(sysUserService.getQueryWrapper(user));
        if (info != null) {
            return R.ok().put("state", "failed");
        } else {
            sysUser.setAddTime(new Date());
            sysUser.setPassword("{MD5}" + MD5.BASE64AndMD5(MD5.Md5("123456")));
            sysUserService.save(sysUser);
            return R.ok().put("state", "success");
        }
    }

    @PostMapping("/edit")
    public R edit(@RequestBody SysUser sysUser){
        sysUserService.updateById(sysUser);
        return R.ok();
    }

    @PostMapping("/delete")
    public R delete(@RequestBody SysUser sysUser){
        sysUserService.removeById(sysUser.getId());
        return R.ok();
    }

    @PostMapping("/reset")
    public R reset(@RequestBody SysUser sysUser) throws Exception{
        sysUser.setPassword("{MD5}" + MD5.BASE64AndMD5(MD5.Md5("123456")));
        sysUserService.updateById(sysUser);
        return R.ok();
    }
}
