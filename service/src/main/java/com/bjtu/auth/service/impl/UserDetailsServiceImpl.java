package com.bjtu.auth.service.impl;

import com.bjtu.auth.service.SysMenuService;
import com.bjtu.auth.service.SysUserService;
import com.bjtu.common.handler.BJTUException;
import com.bjtu.common.result.ResultCodeEnum;
import com.bjtu.model.system.SysUser;
import com.bjtu.security.custom.CustomUser;
import com.bjtu.security.custom.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl  implements UserDetailsService {
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysMenuService sysMenuService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser sysUser = sysUserService.getByUsername(username);
        if(null == sysUser) {
            throw new UsernameNotFoundException("用户名不存在！");
        }

        if(sysUser.getStatus() == 0) {
            throw new BJTUException(ResultCodeEnum.ACCOUNT_STOP);
        }
        List<String> userPermsList = sysMenuService.findUserPermsByUserId(sysUser.getId());
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (String perm : userPermsList) {
            authorities.add(new SimpleGrantedAuthority(perm.trim()));
        }
        return new CustomUser(sysUser, authorities);
    }
}
