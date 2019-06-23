package com.bigtony.ad.search.vo;

import com.bigtony.ad.search.feature.DistrictFeature;
import com.bigtony.ad.search.feature.FeatureRelation;
import com.bigtony.ad.search.feature.ItFeature;
import com.bigtony.ad.search.feature.KeywordFeature;
import com.bigtony.ad.search.media.AdSlot;
import com.bigtony.ad.search.media.App;
import com.bigtony.ad.search.media.Device;
import com.bigtony.ad.search.media.Geo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchRequest {
    
    private String mediaId;
    
    private RequestInfo requestInfo;
    
    private FeatureInfo featureInfo;
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RequestInfo {
        
        private String requestId;
        
        private List<AdSlot> adSlots;
        
        private App app;
        
        private Geo geo;
        
        private Device device;
    }
    
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class FeatureInfo {
        
        private KeywordFeature keywordFeature;
        
        private DistrictFeature districtFeature;
        
        private ItFeature itFeature;
        
        private FeatureRelation relation = FeatureRelation.AND;
    
    }
}
