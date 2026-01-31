package com.smart.stage.sample1.service.impl;

import openjoe.smart.stage.mybatisplus.service.impl.BaseServiceImpl;
import com.smart.stage.sample1.entity.Demo;
import com.smart.stage.sample1.mapper.DemoMapper;
import com.smart.stage.sample1.service.DemoService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.stereotype.Service;

@Service
public class DemoServiceImpl extends BaseServiceImpl<DemoMapper, Demo> implements DemoService {

    @Override
    public Demo getByCode(String code) {
        LambdaQueryWrapper<Demo> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(Demo::getCode, code);
        return getOne(wrapper);
    }
}
