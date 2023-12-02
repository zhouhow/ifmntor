package com.bjtu.pcap2.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.bjtu.common.result.Result;
import com.bjtu.model.pcap1.Alarm;
import com.bjtu.model.pcap2.CommunicationDialogBegin;
import com.bjtu.pcap1.service.AlarmService;
import com.bjtu.pcap2.service.CommunicationDialogBeginService;
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
@RequestMapping("/pcap2/comdiabegin")
public class CommunicationDialogBeginController {
    @Autowired
    private CommunicationDialogBeginService communicationDialogBeginService;
    @ApiOperation(value = "获取")
    @GetMapping("get/{id}")
    public Result findNodes(@PathVariable Long id) {
        return Result.oK(communicationDialogBeginService.getById(id));
    }

    @ApiOperation("查询")
    @GetMapping("/search")
    public Result findAll(PcapQueryVo vo) {
        LambdaQueryWrapper<CommunicationDialogBegin> wrapper = new LambdaQueryWrapper<>();
        if (!StringUtils.isEmpty(vo.getID())) {
            wrapper.eq(CommunicationDialogBegin::getId, vo.getID());
        }
        return Result.oK(communicationDialogBeginService.list(wrapper));
    }

}

