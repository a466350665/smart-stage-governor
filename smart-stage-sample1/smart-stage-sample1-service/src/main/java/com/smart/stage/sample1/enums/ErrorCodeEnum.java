package com.smart.stage.sample1.enums;

import openjoe.smart.stage.core.enums.ErrorCode;

/**
 * 错误码枚举
 */
public enum ErrorCodeEnum implements ErrorCode {

    E010001("010001", "主键不能为空"),
    E010002("010002", "编码【{0}】已存在");

    private String code;
    private String desc;

    ErrorCodeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getDesc() {
        return desc;
    }
}