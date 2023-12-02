package com.bjtu.pcap1.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.bjtu.common.result.Result;
import com.bjtu.model.pcap1.L2cact;
import com.bjtu.pcap1.service.L2cactService;
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
 * @since 2023-04-19
 */
@RestController
@RequestMapping("/pcap1/l2cact")
public class L2cactController {
    @Autowired
    private L2cactService l2cactService;
    @ApiOperation(value = "获取")
    @GetMapping("get/{id}")
    public Result findNodes(@PathVariable Long id) {
        return Result.oK(l2cactService.getById(id));
    }

    @ApiOperation("查询")
    @GetMapping("/search")
    public Result findAll(PcapQueryVo vo) {
        LambdaQueryWrapper<L2cact> wrapper = new LambdaQueryWrapper<>();
        if (!StringUtils.isEmpty(vo.getID())) {
            wrapper.eq(L2cact::getId, vo.getID());
        }
        return Result.oK(l2cactService.list(wrapper));
    }
}

