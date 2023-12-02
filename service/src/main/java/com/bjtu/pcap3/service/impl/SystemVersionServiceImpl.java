package com.bjtu.pcap3.service.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bjtu.model.pcap3.SystemVersion;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bjtu.pcap3.mapper.SystemVersionMapper;
import com.bjtu.pcap3.service.SystemVersionService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author BJTU
 * @since 2023-05-16
 */
@Service
public class SystemVersionServiceImpl extends ServiceImpl<SystemVersionMapper, SystemVersion> implements SystemVersionService {

}
