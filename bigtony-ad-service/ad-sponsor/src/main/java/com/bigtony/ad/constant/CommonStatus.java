package com.bigtony.ad.constant;

import lombok.Getter;

@Getter
public enum CommonStatus {
    VALID(1, "Valid Status"),
    INVALID(0, "Invalid Status");
    
    private Integer status;
    private String desc;
    
    CommonStatus(Integer status, String desc) {
        this.status = status;
        this.desc = desc;
    }
}
