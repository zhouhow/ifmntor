package com.bjtu.auth.service;

import com.bjtu.model.system.SysMenu;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bjtu.vo.system.AssignMenuVo;
import com.bjtu.vo.system.RouterVo;

import java.util.List;

/**
 * <p>
 * 菜单表 服务类
 * </p>
 *
 * @author BJTU
 * @since 2023-03-10
 */
public interface SysMenuService extends IService<SysMenu> {
    List<RouterVo> findUserMenuList(Long userId);
    List<SysMenu> findNodes();
    List<SysMenu> findSysMenuByRoleId(Long roleId);
    void doAssign(AssignMenuVo assignMenuVo);
    List<String> findUserPermsByUserId(Long id);

}
