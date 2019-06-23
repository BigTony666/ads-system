package com.bigtony.ad.controller;

import com.alibaba.fastjson.JSON;
import com.bigtony.ad.annotation.IgnoreResponseAdvice;
import com.bigtony.ad.client.SponsorClient;
import com.bigtony.ad.client.vo.AdPlan;
import com.bigtony.ad.client.vo.AdPlanGetRequest;
import com.bigtony.ad.search.ISearch;
import com.bigtony.ad.search.vo.SearchRequest;
import com.bigtony.ad.search.vo.SearchResponse;
import com.bigtony.ad.vo.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
@RestController
public class SearchController {
    
    private final ISearch search;
    
    private final RestTemplate restTemplate;
    
    private final SponsorClient sponsorClient;
    
    @Autowired
    public SearchController(RestTemplate restTemplate,
                            SponsorClient sponsorClient,
                            ISearch search) {
        this.restTemplate = restTemplate;
        this.sponsorClient = sponsorClient;
        this.search = search;
    }
    
    @PostMapping("/fetchAds")
    public SearchResponse fetchAds(
            @RequestBody SearchRequest request) {
        log.info("ad-search: fetchAds -> {}",
                JSON.toJSONString(request));
        return search.fetchAds(request);
    }
    
    @IgnoreResponseAdvice
    @PostMapping("/getAdPlans")
    public CommonResponse<List<AdPlan>> getAdPlans(
            @RequestBody AdPlanGetRequest request
    ) {
        log.info("ad-search: getAdPlans -> {}",
                JSON.toJSONString(request));
        return sponsorClient.getAdPlans(request);
    }
}
