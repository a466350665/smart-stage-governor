package com.smart.stage.sample2.controller;

import com.smart.stage.sample1.api.DemoApi;
import com.smart.stage.sample1.api.dto.DemoDTO;
import com.smart.stage.sample1.api.dto.DemoRequestDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import openjoe.smart.stage.core.entity.Page;
import openjoe.smart.stage.core.entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "消费者管理")
@RestController
@RequestMapping("/sample2/consumer")
public class ConsumerController {

    @Autowired
    protected DemoApi demoApi;

    @Operation(summary = "跨模块调用列表")
    @GetMapping("/page")
    public Result<Page<DemoDTO>> page() {
        return demoApi.apiPage(new DemoRequestDTO());
    }
}
