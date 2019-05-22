package com.bigtony.ad.service;

import com.bigtony.ad.entity.AdPlan;
import com.bigtony.ad.exception.AdException;
import com.bigtony.ad.vo.AdPlanGetRequest;
import com.bigtony.ad.vo.AdPlanRequest;
import com.bigtony.ad.vo.AdPlanResponse;

import java.util.List;

public interface IAdPlanService {
    
    AdPlanResponse createAdPlan(AdPlanRequest request) throws AdException;
    
    List<AdPlan> getAdPlanByIds(AdPlanGetRequest request) throws AdException;
    
    AdPlanResponse updateAdPlan(AdPlanRequest request) throws AdException;

    void deleteAdPlan(AdPlanRequest request) throws AdException;
}
