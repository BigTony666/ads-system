package com.bigtony.ad.search.media;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class App {

    // Application Code
    private String appCode;
    
    // Application Name
    private String appName;
    
    private String packageName;
    
    private String activityName;
}
