package com.bjtu.auth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bjtu.model.system.SysRole;
import com.bjtu.vo.system.AssginRoleVo;

import java.util.Map;

public interface SysRoleService extends IService<SysRole> {
    Map<String, Object> findRoleByAdminId(Long userId);

    void doAssign(AssginRoleVo assginRoleVo);
}
