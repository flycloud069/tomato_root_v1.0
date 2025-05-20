package com.root.dto;

import lombok.Data;

@Data
public class MetaDto {
    /**
     *标题名称
     */
    private String title;

    public MetaDto(String title){
        this.title=title;

    }
}
