package com.smart.stage.sample1.api;

import openjoe.smart.stage.core.entity.Page;
import openjoe.smart.stage.core.entity.Result;
import com.smart.stage.sample1.api.dto.DemoDTO;
import com.smart.stage.sample1.api.dto.DemoRequestDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * API样例演示
 */
@FeignClient(path = "/sample1/demo", contextId = "demoApi", name = "${smart-stage-sample1.sample1.name:smart-stage-sample1}")
public interface DemoApi {

    /**
     * API分页
     */
    @PostMapping("/api/page")
    Result<Page<DemoDTO>> apiPage(@RequestBody DemoRequestDTO requestDto);
}
