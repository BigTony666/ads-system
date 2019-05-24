package com.bigtony.ad.client;

import com.bigtony.ad.client.vo.AdPlan;
import com.bigtony.ad.client.vo.AdPlanGetRequest;
import com.bigtony.ad.vo.CommonResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SponsorClientHystrix implements SponsorClient {
    
    public CommonResponse<List<AdPlan>> getAdPlans(
            AdPlanGetRequest request) {
        return new CommonResponse<>(-1, "eureka-client-ad-sponsor error");
    }
}
