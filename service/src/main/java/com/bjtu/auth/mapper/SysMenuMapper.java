package com.bjtu.auth.mapper;

import com.bjtu.model.system.SysMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * <p>
 * 菜单表 Mapper 接口
 * </p>
 *
 * @author BJTU
 * @since 2023-03-10
 */
@Repository
@Mapper
public interface SysMenuMapper extends BaseMapper<SysMenu> {
    List<SysMenu> findListByUserId(@Param("userId") Long userId);
}
