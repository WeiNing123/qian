package com.wn.qian.modules.main.controller;

import com.wn.qian.framework.base.BaseController;
import com.wn.qian.modules.main.model.SysUser;
import com.wn.qian.modules.main.service.ISysUserService;
import com.wn.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/main/user")
public class SysUserController extends BaseController {

    @Autowired
    private ISysUserService sysUserService;

    @PostMapping("/list")
    public R list(@RequestBody SysUser sysUser) {
        List<SysUser> result = sysUserService.list(sysUserService.getQueryWrapper(sysUser));
        return R.ok().put("data", result);
    }
}
