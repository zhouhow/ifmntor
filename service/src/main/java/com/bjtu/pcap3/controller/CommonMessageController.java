package com.bjtu.pcap3.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.bjtu.common.result.Result;
import com.bjtu.model.pcap3.AcceptTrain;
import com.bjtu.model.pcap3.CommonMessage;
import com.bjtu.pcap3.service.AcceptTrainService;
import com.bjtu.pcap3.service.CommonMessageService;
import com.bjtu.vo.pcap.PcapQueryVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author BJTU
 * @since 2023-05-16
 */
@RestController
@RequestMapping("/pcap3/commes")
public class CommonMessageController {
    @Autowired
    private CommonMessageService commonMessageService;
    @ApiOperation(value = "获取")
    @GetMapping("get/{id}")
    public Result findNodes(@PathVariable Long id) {
        return Result.oK(commonMessageService.getById(id));
    }

    @ApiOperation("查询")
    @GetMapping("/search")
    public Result findAll(PcapQueryVo vo) {
        LambdaQueryWrapper<CommonMessage> wrapper = new LambdaQueryWrapper<>();
        if (!StringUtils.isEmpty(vo.getID())) {
            wrapper.eq(CommonMessage::getId, vo.getID());
        }
        return Result.oK(commonMessageService.list(wrapper));
    }
}

