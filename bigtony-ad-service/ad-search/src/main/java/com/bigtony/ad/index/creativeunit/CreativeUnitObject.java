package com.bigtony.ad.index.creativeunit;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreativeUnitObject {
    
    // key: adId-unitId
    private Long adId;
    private Long unitId;
    
}