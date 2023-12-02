package com.bjtu.common;


import com.bjtu.model.system.SysMenu;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 根据菜单数据构建菜单数据
 * </p>
 */
public class MenuHelper {

    /**
     * 使用递归方法建菜单
     */
    public static List<SysMenu> buildTree(List<SysMenu> sysMenuList) {
        List<SysMenu> trees = new ArrayList<>();
        for (SysMenu sysMenu : sysMenuList)
            if (sysMenu.getParentId() == 0) trees.add(findChildren(sysMenu, sysMenuList));
        return trees;
    }

    /**
     * 递归查找子节点
     */
    public static SysMenu findChildren(SysMenu sysMenu, List<SysMenu> treeNodes) {
        sysMenu.setChildren(new ArrayList<>());

        for (SysMenu it : treeNodes) {
            if (sysMenu.getId().longValue() == it.getParentId().longValue()) {
                if (sysMenu.getChildren() == null) {
                    sysMenu.setChildren(new ArrayList<>());
                }
                sysMenu.getChildren().add(findChildren(it, treeNodes));
            }
        }
        return sysMenu;
    }
}