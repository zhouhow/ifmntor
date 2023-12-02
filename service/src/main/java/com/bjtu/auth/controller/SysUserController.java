package com.bjtu.auth.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bjtu.auth.service.SysUserService;
import com.bjtu.common.MD5.MD5;
import com.bjtu.common.result.Result;
import com.bjtu.model.system.SysUser;
import com.bjtu.vo.system.SysUserQueryVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author BJTU
 * @since 2023-03-10
 */
@ApiOperation("用户管理api")
@RestController
@RequestMapping("/admin/system/sysUser")
public class SysUserController {
    @Autowired
    private SysUserService service;
    @ApiOperation(value = "分页查询 ")
    @GetMapping("{page}/{limit}")
    public Result get(@PathVariable Long page, @PathVariable Long limit, SysUserQueryVo vo){
        Page<SysUser> page1 = new Page<>(page,limit);
        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<>();
        String keyword = vo.getKeyword();
        String createTimeEnd = vo.getCreateTimeEnd();
        String createTimeBegin = vo.getCreateTimeBegin();
        if(!StringUtils.isEmpty(keyword)) wrapper.like(SysUser::getUsername, keyword);
        if(!StringUtils.isEmpty(createTimeBegin)) wrapper.ge(SysUser::getCreateTime, createTimeBegin);
        if(!StringUtils.isEmpty(createTimeEnd)) wrapper.le(SysUser::getCreateTime, createTimeEnd);
        service.page(page1,wrapper);
        return Result.oK(page1);
    }

    @ApiOperation(value = "获取用户")
    @GetMapping("get/{id}")
    public Result get(@PathVariable Long id) {
        SysUser user = service.getById(id);
        return Result.oK(user);
    }

    @ApiOperation(value = "添加用户")
    @PostMapping("add")
    public Result add(@RequestBody SysUser user) {
        user.setPassword(MD5.encrypt(user.getPassword()));
        service.save(user);
        return Result.oK();
    }

    @ApiOperation(value = "编辑用户")
    @PutMapping("edit")
    public Result editById(@RequestBody SysUser user) {
        service.updateById(user);
        return Result.oK();
    }

    @ApiOperation(value = "删除用户")
    @DeleteMapping("delete/{id}")
    public Result remove(@PathVariable Long id) {
        service.removeById(id);
        return Result.oK();
    }

    @ApiOperation(value = "更新状态")
    @GetMapping("updateStatus/{id}/{status}")
    public Result updateStatus(@PathVariable Long id, @PathVariable Integer status) {
        service.updateStatus(id, status);
        return Result.oK();
    }

}

