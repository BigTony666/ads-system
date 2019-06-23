package com.bigtony.ad.search;

import com.alibaba.fastjson.JSON;
import com.bigtony.ad.Application;
import com.bigtony.ad.search.vo.SearchRequest;
import com.bigtony.ad.search.vo.feature.DistrictFeature;
import com.bigtony.ad.search.vo.feature.FeatureRelation;
import com.bigtony.ad.search.vo.feature.ItFeature;
import com.bigtony.ad.search.vo.feature.KeywordFeature;
import com.bigtony.ad.search.vo.media.AdSlot;
import com.bigtony.ad.search.vo.media.App;
import com.bigtony.ad.search.vo.media.Device;
import com.bigtony.ad.search.vo.media.Geo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class},
                webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class SearchTest {
    
    @Autowired
    private ISearch search;
    
    @Test
    public void testFetchAds() {
    
        SearchRequest request = new SearchRequest();
        
        request.setMediaId("media-id-test");
        
        // First Test
        request.setRequestInfo(new SearchRequest.RequestInfo(
                "req-1",
                Collections.singletonList(new AdSlot(
                        "ad-slot-code-1", 1,
                        1080, 720, Arrays.asList(1, 2),
                        1000
                )),
                buildExampleApp(),
                buildExampleGeo(),
                buildExampleDevice()
        ));
        request.setFeatureInfo(buildExampleFeatureInfo(
                Arrays.asList("BMW", "Benz"),
                Collections.singletonList(
                        new DistrictFeature.StateAndCity(
                                "MA", "Boston")),
                Arrays.asList("Family", "Sport"),
                FeatureRelation.OR
        ));
        System.out.println(JSON.toJSONString(request));
        System.out.println(JSON.toJSONString(search.fetchAds(request)));
        
        // Second Test
        request.setRequestInfo(new SearchRequest.RequestInfo(
                "req-2",
                Collections.singletonList(new AdSlot(
                        "ad-slot-code-2", 1,
                        1080, 720, Arrays.asList(1, 2),
                        1000
                )),
                buildExampleApp(),
                buildExampleGeo(),
                buildExampleDevice()
        ));
        request.setFeatureInfo(buildExampleFeatureInfo(
                Arrays.asList("BMW", "Audi", "Lamborghini"),
                Collections.singletonList(
                        new DistrictFeature.StateAndCity(
                                "MA", "Boston")),
                Arrays.asList("Family", "Sport"),
                FeatureRelation.AND
        ));
        System.out.println(JSON.toJSONString(request));
        System.out.println(JSON.toJSONString(search.fetchAds(request)));
    }
    
    private App buildExampleApp() {
        return new App("bigtony-101", "bigtony-facebook",
                "com.bigtony", "video");
    }
    
    private Geo buildExampleGeo() {
        return new Geo((float) 42.20, (float) -71.05,
                "Boston", "MA");
    }
    
    private Device buildExampleDevice() {
        
        return new Device(
                "iphone",
                "0xxxxx",
                "127.0.0.1",
                "x",
                "1080 720",
                "1080 720",
                "123456789"
        );
    }
    
    private SearchRequest.FeatureInfo buildExampleFeatureInfo(
            List<String> keywords,
            List<DistrictFeature.StateAndCity> provinceAndCities,
            List<String> its,
            FeatureRelation relation
    ) {
        return new SearchRequest.FeatureInfo(
                new KeywordFeature(keywords),
                new DistrictFeature(provinceAndCities),
                new ItFeature(its),
                relation
        );
    }
}
