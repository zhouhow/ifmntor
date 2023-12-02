package com.bjtu.pcap1.mapper;

import com.bjtu.model.pcap1.TrainNumberVerify;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author BJTU
 * @since 2023-04-03
 */
@Repository
@Mapper
public interface TraNumVerifyMapper extends BaseMapper<TrainNumberVerify> {

}
