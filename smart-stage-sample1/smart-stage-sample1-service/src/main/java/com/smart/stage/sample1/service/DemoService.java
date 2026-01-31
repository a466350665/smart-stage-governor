package com.smart.stage.sample1.service;

import openjoe.smart.stage.mybatisplus.service.BaseService;
import com.smart.stage.sample1.entity.Demo;

public interface DemoService extends BaseService<Demo> {

    Demo getByCode(String code);
}