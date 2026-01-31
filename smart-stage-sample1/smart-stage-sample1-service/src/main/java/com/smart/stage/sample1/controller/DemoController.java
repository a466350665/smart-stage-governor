package com.smart.stage.sample1.controller;

import openjoe.smart.stage.core.entity.Page;
import openjoe.smart.stage.core.entity.Result;
import openjoe.smart.stage.exception.ApplicationException;
import com.smart.stage.sample1.enums.ErrorCodeEnum;
import com.smart.stage.sample1.entity.Demo;
import com.smart.stage.sample1.service.DemoService;
import com.smart.stage.sample1.api.DemoApi;
import com.smart.stage.sample1.api.dto.DemoDTO;
import com.smart.stage.sample1.api.dto.DemoRequestDTO;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "基础增删查改样例演示")
@RequestMapping("/sample1/demo")
@RestController
public class DemoController implements DemoApi {

    @Autowired
    protected DemoService demoService;

    @Operation(summary = "分页")
    @GetMapping("/page")
    public Result<Page<Demo>> page(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "current", defaultValue = "1") Long current,
            @RequestParam(value = "size", defaultValue = "10") Long size) {
        LambdaQueryWrapper<Demo> wrapper = Wrappers.lambdaQuery();
        wrapper.like(StringUtils.isNotBlank(name), Demo::getName, name);
        return Result.success(demoService.findPage(current, size, wrapper));
    }

    @Operation(summary = "API分页")
    @Override
    public Result<Page<DemoDTO>> apiPage(DemoRequestDTO requestDto) {
        Result<Page<Demo>> result = page(requestDto.getName(), requestDto.getCurrent(), requestDto.getSize());
        return Result.success(result.getData().convert(t -> {
            DemoDTO dto = new DemoDTO();
            BeanUtils.copyProperties(t, dto);
            return dto;
        }));
    }

    @Operation(summary = "列表")
    @GetMapping("/list")
    public Result<List<Demo>> list() {
        return Result.success(demoService.list());
    }

    @Operation(summary = "新增")
    @PostMapping
    public Result add(@RequestBody @Validated Demo entity) {
        Demo t = demoService.getByCode(entity.getCode());
        if (t != null) {
            throw new ApplicationException(ErrorCodeEnum.E010002, entity.getCode());
        }
        demoService.save(entity);
        return Result.success();
    }

    @Operation(summary = "修改")
    @PutMapping
    public Result update(@RequestBody @Validated Demo entity) {
        if (entity.getId() == null) {
            throw new ApplicationException(ErrorCodeEnum.E010001);
        }
        Demo t = demoService.getByCode(entity.getCode());
        if (t != null && !t.getId().equals(entity.getId())) {
            throw new ApplicationException(ErrorCodeEnum.E010002, entity.getCode());
        }
        demoService.updateById(entity);
        return Result.success();
    }

    @Operation(summary = "删除")
    @DeleteMapping({"/{id}"})
    public Result deleteById(@PathVariable("id") Long id) {
        demoService.removeById(id);
        return Result.success();
    }
}
