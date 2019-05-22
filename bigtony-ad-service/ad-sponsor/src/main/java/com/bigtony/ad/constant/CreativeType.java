package com.bigtony.ad.constant;

import lombok.Getter;

@Getter
public enum CreativeType {
    IMAGE(1, "Image"),
    VIDEO(2, "Video"),
    TEXT(3, "Text");
    
    private int type;
    private String desc;
    
    CreativeType(int type, String desc) {
        this.type = type;
        this.desc = desc;
    }
}
