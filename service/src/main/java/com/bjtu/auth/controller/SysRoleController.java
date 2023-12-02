package com.bjtu.auth.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bjtu.auth.service.SysRoleService;
import com.bjtu.common.result.Result;
import com.bjtu.model.system.SysRole;
import com.bjtu.vo.system.AssginRoleVo;
import com.bjtu.vo.system.SysRoleQueryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Api(tags = "角色管理")
@RestController
@RequestMapping("/admin/system/sysRole")
public class SysRoleController {
    @Autowired
    private SysRoleService service;

    @ApiOperation("查询全部")
    @GetMapping("/findAll")
    public Result findAll() {
        List<SysRole> list = service.list();
        return Result.oK(list);
    }
    @PreAuthorize("hasAuthority('bnt.sysRole.list')")
    @ApiOperation("分页条件查询")
    @GetMapping("{page}/{limit}")
    public Result findSplit(@PathVariable Long page, @PathVariable Long limit, SysRoleQueryVo vo) {
        Page<SysRole> page1 = new Page<>(page, limit);
        LambdaQueryWrapper<SysRole> wrapper = new LambdaQueryWrapper<>();
        if (!StringUtils.isEmpty(vo.getRoleName())) {
            wrapper.like(SysRole::getRoleName, vo.getRoleName());
        }
        service.page(page1, wrapper);
        return Result.oK(page1);
    }
    @PreAuthorize("hasAuthority('bnt.sysRole.add')")
    @ApiOperation("增加角色")
    @PostMapping("add")
    public Result add(@RequestBody @Validated SysRole sysRole) {
        boolean Success = service.save(sysRole);
        if (Success) return Result.oK();
        else return Result.fail();
    }
    @PreAuthorize("hasAuthority('bnt.sysRole.list')")
    @ApiOperation("根据ID查询")
    @GetMapping("get/{id}")
    public Result getById(@PathVariable Long id) {
        return Result.oK(service.getById(id));
    }
    @PreAuthorize("hasAuthority('bnt.sysRole.update')")
    @ApiOperation("根据ID编辑")
    @PutMapping("edit")
    public Result updateById(@RequestBody SysRole sysRole) {
        service.updateById(sysRole);
        return Result.oK();
    }
    @PreAuthorize("hasAuthority('bnt.sysRole.remove')")
    @ApiOperation("根据ID删除")
    @DeleteMapping("delete/{id}")
    public Result deleteById(@PathVariable Long id) {
        boolean Success = service.removeById(id);
        if (Success) return Result.oK();
        else return Result.fail();
    }
    @PreAuthorize("hasAuthority('bnt.sysRole.remove')")
    @ApiOperation("批量删除")
    @DeleteMapping("batchdelete")
    public Result deleteBatchId(@RequestBody List<Long> idList) {
        boolean Success = service.removeByIds(idList);
        if (Success) return Result.oK();
        else return Result.fail();
    }

    @ApiOperation(value = "根据用户获取角色数据")
    @GetMapping("/toAssign/{userId}")
    public Result toAssign(@PathVariable Long userId) {
        Map<String, Object> roleMap = service.findRoleByAdminId(userId);
        return Result.oK(roleMap);
    }

    @ApiOperation(value = "根据用户分配角色")
    @PostMapping("/doAssign")
    public Result doAssign(@RequestBody AssginRoleVo assginRoleVo) {
        service.doAssign(assginRoleVo);
        return Result.oK();
    }
}
