package com.bigtony.ad.index;

import lombok.Getter;

@Getter
public enum CommonStatus {
    
    VALID(1, "Valid"),
    INVALID(0, "Invalid");
    
    private Integer status;
    private String desc;
    
    CommonStatus(Integer status, String desc) {
        this.status = status;
        this.desc = desc;
    }
}
