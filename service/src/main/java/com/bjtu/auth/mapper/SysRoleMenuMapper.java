package com.bjtu.auth.mapper;

import com.bjtu.model.system.SysRoleMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 角色菜单 Mapper 接口
 * </p>
 *
 * @author BJTU
 * @since 2023-03-10
 */
@Repository
@Mapper
public interface SysRoleMenuMapper extends BaseMapper<SysRoleMenu> {

}
