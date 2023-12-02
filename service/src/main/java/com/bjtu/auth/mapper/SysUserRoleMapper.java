package com.bjtu.auth.mapper;

import com.bjtu.model.system.SysUserRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户角色 Mapper 接口
 * </p>
 *
 * @author BJTU
 * @since 2023-03-10
 */
@Repository
@Mapper
public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {

}
