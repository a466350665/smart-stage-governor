package com.smart.stage.sample1.api.dto;

import openjoe.smart.stage.core.entity.PageRequest;

public class DemoRequestDTO extends PageRequest {

    /**
     * 名称
     */
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
