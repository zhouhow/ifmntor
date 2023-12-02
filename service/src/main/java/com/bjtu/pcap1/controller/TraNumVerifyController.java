package com.bjtu.pcap1.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.bjtu.common.result.Result;
import com.bjtu.model.pcap1.TrainNumberVerify;
import com.bjtu.pcap1.service.TraNumVerifyService;
import com.bjtu.vo.pcap.PcapQueryVo;
import io.swagger.annotations.Api;
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
 * @since 2023-04-03
 */

@RestController
@RequestMapping("/pcap1/tranumverify")
@Api(tags = "无线车次号确认")
public class TraNumVerifyController {
    @Autowired
    private TraNumVerifyService traNumVerifyService;
    @ApiOperation(value = "获取")
    @GetMapping("get/{id}")
    public Result findNodes(@PathVariable Long id) {
        return Result.oK(traNumVerifyService.getById(id));
    }

    @ApiOperation("查询")
    @GetMapping("/search")
    public Result findAll(PcapQueryVo vo) {
        LambdaQueryWrapper<TrainNumberVerify> wrapper = new LambdaQueryWrapper<>();
        if (!StringUtils.isEmpty(vo.getTrainID())) {
            wrapper.eq(TrainNumberVerify::getTrainNumber, vo.getTrainID());
        }
        return Result.oK(traNumVerifyService.list(wrapper));
    }
}

