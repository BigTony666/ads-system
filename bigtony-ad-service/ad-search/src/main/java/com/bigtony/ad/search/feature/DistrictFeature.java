package com.bigtony.ad.search.feature;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DistrictFeature {
    
    private List<StateAndCity> districts;
    
    public static class StateAndCity {
        
        private String state;
        
        private String city;
    }
}
