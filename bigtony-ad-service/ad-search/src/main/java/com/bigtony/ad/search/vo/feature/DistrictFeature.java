package com.bigtony.ad.search.vo.feature;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DistrictFeature {
    
    private List<StateAndCity> districts;
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class StateAndCity {
        
        private String state;
        
        private String city;
    }
}
