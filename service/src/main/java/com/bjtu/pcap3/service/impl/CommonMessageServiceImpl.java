package com.bjtu.pcap3.service.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bjtu.model.pcap3.CommonMessage;
import com.bjtu.pcap3.mapper.CommonMessageMapper;
import com.bjtu.pcap3.service.CommonMessageService;
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
public class CommonMessageServiceImpl extends ServiceImpl<CommonMessageMapper, CommonMessage> implements CommonMessageService {

}
