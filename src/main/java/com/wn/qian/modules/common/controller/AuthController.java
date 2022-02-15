package com.wn.qian.modules.common.controller;

import com.wn.qian.framework.base.BaseController;
import com.wn.qian.modules.main.model.SysUser;
import com.wn.qian.modules.main.service.ISysUserService;
import com.wn.utils.CryptoUtil;
import com.wn.utils.MD5;
import com.wn.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController extends BaseController {

    @Autowired
    private ISysUserService sysUserService;

    @PostMapping("/login")
    public R login(@RequestBody SysUser user) throws Exception{
        user.setPassword("{MD5}" + MD5.BASE64AndMD5(user.getPassword()));
        SysUser info = sysUserService.getOne(sysUserService.getQueryWrapper(user));
        if (info == null) return R.error(301, "用户名或密码错误！");
        getRedisUtil().set("user_code", info.getUserCode());

        long time = new Date().getTime()/1000;
        String token = CryptoUtil.encodeSrc(info.getUserCode() + "&@&" + time);

        Map<String, Object> json = new HashMap<>();
        json.put("username", info.getUserCode());
        json.put("roleId", info.getId());
        json.put("token", token);

        return R.ok().put("result", json);
    }

    @GetMapping("/logout")
    public R logout() {
        return R.ok();
    }

    @PostMapping("/modifyPassword")
    public R modifyPassword(@RequestBody SysUser user) throws Exception{
        user.setPassword("{MD5}" + MD5.BASE64AndMD5(MD5.Md5(user.getPassword())));
        SysUser info = sysUserService.getOne(sysUserService.getQueryWrapper(user));
        if (info == null) {
            return R.ok().put("state", 0);
        } else {
            user.setPassword("{MD5}" + MD5.BASE64AndMD5(MD5.Md5(user.getNewPassword())));
            sysUserService.updateById(user);
            return R.ok().put("state", 1);
        }
    }
}
