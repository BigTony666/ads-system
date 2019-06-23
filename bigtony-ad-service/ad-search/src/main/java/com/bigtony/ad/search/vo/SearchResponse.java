package com.bigtony.ad.search.vo;

import com.bigtony.ad.index.creative.CreativeObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchResponse {
    
    // One adSlot may have multiple creatives
    public Map<String, List<Creative>> adSlot2Ads = new HashMap<>();
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Creative {
        
        private Long adId;
        private String adUrl;
        private Integer width;
        private Integer height;
        private Integer type;
        private Integer materialType;
        
        // Display Monitor url
        private List<String> showMonitorUtl =
                Arrays.asList("bigtony666.github.io", "bigtony666.github.io");
    
        // Click Monitor url
        private List<String> clickMonitorUrl =
                Arrays.asList("bigtony666.github.io", "bigtony666.github.io");
    }
    
    public static Creative convert(CreativeObject object) {
        
        Creative creative = new Creative();
    
        creative.setAdId(object.getAdId());
        creative.setAdUrl(object.getAdUrl());
        creative.setWidth(object.getWidth());
        creative.setHeight(object.getHeight());
        creative.setType(object.getType());
        creative.setMaterialType(object.getMaterialType());
    
        return creative;
    }
}
