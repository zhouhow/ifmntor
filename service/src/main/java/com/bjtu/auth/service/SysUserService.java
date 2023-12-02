package com.bjtu.auth.service;

import com.bjtu.model.system.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author BJTU
 * @since 2023-03-10
 */
public interface SysUserService extends IService<SysUser> {
    void updateStatus(Long id, Integer status);

    SysUser getByUsername(String username);

    Map<String, Object> getUserInfo(String username);
}
