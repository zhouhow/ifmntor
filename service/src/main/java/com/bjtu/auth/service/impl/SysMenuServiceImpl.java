package com.bjtu.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.bjtu.auth.mapper.SysRoleMenuMapper;
import com.bjtu.auth.service.SysRoleMenuService;
import com.bjtu.common.MenuHelper;
import com.bjtu.common.handler.BJTUException;
import com.bjtu.model.system.SysMenu;
import com.bjtu.auth.mapper.SysMenuMapper;
import com.bjtu.auth.service.SysMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bjtu.model.system.SysRoleMenu;
import com.bjtu.vo.system.AssignMenuVo;
import com.bjtu.vo.system.MetaVo;
import com.bjtu.vo.system.RouterVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 菜单表 服务实现类
 * </p>
 *
 * @author BJTU
 * @since 2023-03-10
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {

    @Autowired
    private SysRoleMenuService sysRoleMenuService;
    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;
    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Override
    public List<RouterVo> findUserMenuList(Long userId) {
        //超级管理员admin账号id为：1
        List<SysMenu> sysMenuList = null;
        if (userId == 1) {
            sysMenuList = this.list(new LambdaQueryWrapper<SysMenu>().eq(SysMenu::getStatus, 1)
                    .orderByAsc(SysMenu::getSortValue));
        } else {
            sysMenuList = sysMenuMapper.findListByUserId(userId);
        }
        //构建树形数据
        List<SysMenu> sysMenuTreeList = MenuHelper.buildTree(sysMenuList);

        return this.buildMenus(sysMenuTreeList);
    }

    /**
     * 根据菜单构建路由
     */
    private List<RouterVo> buildMenus(List<SysMenu> menus) {
        List<RouterVo> routers = new LinkedList<>();
        for (SysMenu menu : menus) {
            RouterVo router = loadRoute(false, menu);
            List<SysMenu> children = menu.getChildren();
            //如果当前是菜单，需将按钮对应的路由加载出来，如：“角色授权”按钮对应的路由在“系统管理”下面
            if (menu.getType() == 1) {
                List<SysMenu> hiddenMenuList = children.stream().filter(
                        item -> !StringUtils.isEmpty(item.getComponent())).collect(Collectors.toList());
                for (SysMenu hiddenMenu : hiddenMenuList) routers.add(loadRoute(true, hiddenMenu));
            } else {
                if (!CollectionUtils.isEmpty(children)) {
                    if (children.size() > 0) {
                        router.setAlwaysShow(true);
                    }
                    router.setChildren(buildMenus(children));
                }
            }
            routers.add(router);
        }
        return routers;
    }

    /**
     * 获取路由地址
     *
     * @param menu 菜单信息
     * @return 路由地址
     */
    public String getRouterPath(SysMenu menu) {
        return menu.getParentId().intValue() != 0 ? menu.getPath() : "/" + menu.getPath();
    }

    @Override
    public List<SysMenu> findNodes() {
        //全部权限列表
        List<SysMenu> sysMenuList = this.list();
        if (CollectionUtils.isEmpty(sysMenuList)) return null;

        //构建树形数据
        return MenuHelper.buildTree(sysMenuList);
    }
    /**
     * 根据ID加载菜单
     */
    @Override
    public List<SysMenu> findSysMenuByRoleId(Long roleId) {
        //全部权限列表
        List<SysMenu> allSysMenuList = this.list(new LambdaQueryWrapper<SysMenu>().eq(SysMenu::getStatus, 1));

        //根据角色id获取角色权限
        List<SysRoleMenu> sysRoleMenuList = sysRoleMenuService.list(
                new LambdaQueryWrapper<SysRoleMenu>().eq(SysRoleMenu::getRoleId, roleId));
        //转换给角色id与角色权限对应Map对象
        List<Long> menuIdList = sysRoleMenuList.stream().map(SysRoleMenu::getMenuId).collect(Collectors.toList());

        allSysMenuList.forEach(permission -> permission.setSelect(menuIdList.contains(permission.getId())));

        return MenuHelper.buildTree(allSysMenuList);
    }
    /**
     * 更新许可
     */
    @Transactional
    @Override
    public void doAssign(AssignMenuVo assginMenuVo) {
//        sysRoleMenuService.remove(new LambdaQueryWrapper<SysRoleMenu>().eq(SysRoleMenu::getRoleId, assginMenuVo.getRoleId()));
        sysRoleMenuMapper.delete(new LambdaQueryWrapper<SysRoleMenu>().eq(SysRoleMenu::getRoleId, assginMenuVo.getRoleId()));
        for (Long menuId : assginMenuVo.getMenuIdList()) {
            if (StringUtils.isEmpty(menuId)) continue;
            SysRoleMenu rolePermission = new SysRoleMenu();
            rolePermission.setRoleId(assginMenuVo.getRoleId());
            rolePermission.setMenuId(menuId);
            sysRoleMenuService.save(rolePermission);
        }
    }
    /**
     * 根据ID查询许可
     */
    @Override
    public List<String> findUserPermsByUserId(Long id) {
        List<SysMenu> sysMenuList;
        if (id == 1) {
            sysMenuList = this.list(new LambdaQueryWrapper<SysMenu>().eq(SysMenu::getStatus, 1)
                    .orderByAsc(SysMenu::getSortValue));
        } else {
            sysMenuList = sysMenuMapper.findListByUserId(id);
        }
        return sysMenuList.stream()
                .filter(item -> item.getType() == 2)
                .map(SysMenu::getPerms)
                .collect(Collectors.toList());
    }
    /**
     * 根据ID删除菜单
     */
    @Override
    public boolean removeById(Serializable id) {
        int count = this.count(new LambdaQueryWrapper<SysMenu>().eq(SysMenu::getParentId, id));
        if (count > 0) {
            throw new BJTUException(201, "菜单不能删除, 因为它有子菜单");
        }
        baseMapper.deleteById(id);
        return false;
    }

    /**
     * 根据菜单加载路由
     */
    RouterVo loadRoute(boolean flag, SysMenu menu) {
        RouterVo router = new RouterVo();
        router.setHidden(flag);
        router.setAlwaysShow(false);
        router.setPath(getRouterPath(menu));
        router.setComponent(menu.getComponent());
        router.setMeta(new MetaVo(menu.getName(), menu.getIcon()));
        return router;
    }
}
