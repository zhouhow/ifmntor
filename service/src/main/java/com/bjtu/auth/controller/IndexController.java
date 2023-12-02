package com.bjtu.auth.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.bjtu.auth.service.SysMenuService;
import com.bjtu.auth.service.SysUserService;
import com.bjtu.common.MD5.MD5;
import com.bjtu.common.handler.BJTUException;
import com.bjtu.common.jwt.JwtHelper;
import com.bjtu.common.result.Result;
import com.bjtu.model.system.SysUser;
import com.bjtu.vo.system.LoginVo;
import com.bjtu.vo.system.RouterVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 后台登录登出
 * </p>
 */
@Api(tags = "后台登录管理")
@RestController
@RequestMapping("/admin/system/index")
public class IndexController {

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysMenuService sysMenuService;

    @ApiOperation(value = "登录")
    @PostMapping("login")
    public Result login(@RequestBody LoginVo loginVo) {
        SysUser sysUser = sysUserService.getOne(
                new LambdaQueryWrapper<SysUser>().eq(SysUser::getUsername, loginVo.getUsername()));
        if(null == sysUser) {
            throw new BJTUException(201,"用户不存在");
        }
        if(!MD5.encrypt(loginVo.getPassword()).equals(sysUser.getPassword())) {
            throw new BJTUException(201,"密码错误");
        }
        if(sysUser.getStatus() == 0) {
            throw new BJTUException(201,"用户被禁用");
        }

        Map<String, Object> map = new HashMap<>();
        map.put("token", JwtHelper.createToken(sysUser.getId(), sysUser.getUsername()));
        return Result.oK(map);
    }
    /**
     * 获取用户信息
     */
    @GetMapping("info")
    public Result info(HttpServletRequest request) {
        String username = JwtHelper.getUsername(request.getHeader("token"));
        Map<String, Object> map = sysUserService.getUserInfo(username);
        return Result.oK(map);
    }
    /**
     * 退出
     */
    @PostMapping("logout")
    public Result logout(){
        return Result.oK();
    }

}