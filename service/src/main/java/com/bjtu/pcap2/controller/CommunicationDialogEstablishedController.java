package com.bjtu.pcap2.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.bjtu.common.result.Result;
import com.bjtu.model.pcap2.CommunicationDialogEnd;
import com.bjtu.model.pcap2.CommunicationDialogEstablished;
import com.bjtu.pcap2.service.CommunicationDialogEndService;
import com.bjtu.pcap2.service.CommunicationDialogEstablishedService;
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
@RequestMapping("/pcap2/comdiaest")
public class CommunicationDialogEstablishedController {
    @Autowired
    private CommunicationDialogEstablishedService communicationDialogEstablishedService;
    @ApiOperation(value = "获取")
    @GetMapping("get/{id}")
    public Result findNodes(@PathVariable Long id) {
        return Result.oK(communicationDialogEstablishedService.getById(id));
    }

    @ApiOperation("查询")
    @GetMapping("/search")
    public Result findAll(PcapQueryVo vo) {
        LambdaQueryWrapper<CommunicationDialogEstablished> wrapper = new LambdaQueryWrapper<>();
        if (!StringUtils.isEmpty(vo.getID())) {
            wrapper.eq(CommunicationDialogEstablished::getId, vo.getID());
        }
        return Result.oK(communicationDialogEstablishedService.list(wrapper));
    }
}

