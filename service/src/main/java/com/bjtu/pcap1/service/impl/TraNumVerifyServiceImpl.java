package com.bjtu.pcap1.service.impl;

import com.bjtu.model.pcap1.TrainNumberVerify;
import com.bjtu.pcap1.mapper.TraNumVerifyMapper;
import com.bjtu.pcap1.service.TraNumVerifyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author BJTU
 * @since 2023-04-03
 */
@Service
public class TraNumVerifyServiceImpl extends ServiceImpl<TraNumVerifyMapper, TrainNumberVerify> implements TraNumVerifyService {
}
