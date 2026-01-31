package com.smart.stage.sample1.entity;

import openjoe.smart.stage.mybatisplus.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;

import jakarta.validation.constraints.NotBlank;

@TableName("t_demo")
public class Demo extends BaseEntity {

    @Schema(description = "编码")
    @NotBlank
    private String code;

    @Schema(description = "名称")
    @NotBlank
    private String name;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}