package com.bigtony.ad.search.impl;

import com.alibaba.fastjson.JSON;
import com.bigtony.ad.index.CommonStatus;
import com.bigtony.ad.index.DataTable;
import com.bigtony.ad.index.adunit.AdUnitIndex;
import com.bigtony.ad.index.adunit.AdUnitObject;
import com.bigtony.ad.index.creative.CreativeIndex;
import com.bigtony.ad.index.creative.CreativeObject;
import com.bigtony.ad.index.creativeunit.CreativeUnitIndex;
import com.bigtony.ad.index.district.UnitDistrictIndex;
import com.bigtony.ad.index.interest.UnitItIndex;
import com.bigtony.ad.index.keyworkd.UnitKeywordIndex;
import com.bigtony.ad.search.ISearch;
import com.bigtony.ad.search.vo.SearchRequest;
import com.bigtony.ad.search.vo.SearchResponse;
import com.bigtony.ad.search.vo.feature.DistrictFeature;
import com.bigtony.ad.search.vo.feature.FeatureRelation;
import com.bigtony.ad.search.vo.feature.ItFeature;
import com.bigtony.ad.search.vo.feature.KeywordFeature;
import com.bigtony.ad.search.vo.media.AdSlot;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class SearchImpl implements ISearch {
    
    @Override
    public SearchResponse fetchAds(SearchRequest request) {
        
        List<AdSlot> adSlots = request.getRequestInfo().getAdSlots();
        
        KeywordFeature keywordFeature =
                request.getFeatureInfo().getKeywordFeature();
        DistrictFeature districtFeature =
                request.getFeatureInfo().getDistrictFeature();
        ItFeature itFeature =
                request.getFeatureInfo().getItFeature();
        
        FeatureRelation relation = request.getFeatureInfo().getRelation();
        
        SearchResponse response = new SearchResponse();
        Map<String, List<SearchResponse.Creative>> adSlot2Ads =
                response.getAdSlot2Ads();
        
        for (AdSlot adSlot : adSlots) {
            
            Set<Long> targetUnitIdSet;
            
            // Step 1: Filter adUnits by Position Type
            Set<Long> adUnitIdSet = DataTable.of(
                    AdUnitIndex.class
            ).match(adSlot.getPositionType());
            
            // Step 2: Filter adUnits by Relation,
            if (relation == FeatureRelation.AND) {
                
                filterKeywordFeature(adUnitIdSet, keywordFeature);
                filterDistrictFeature(adUnitIdSet, districtFeature);
                filterItTagFeature(adUnitIdSet, itFeature);
                
                targetUnitIdSet = adUnitIdSet;
                
            } else {
                targetUnitIdSet = getORRelationUnitIds(
                        adUnitIdSet,
                        keywordFeature,
                        districtFeature,
                        itFeature
                );
            }
            
            List<AdUnitObject> unitObjects =
                    DataTable.of(AdUnitIndex.class).fetch(targetUnitIdSet);
            
            // Step 3: Filter adUnits by status
            filterAdUnitAndPlanStatus(unitObjects, CommonStatus.VALID);
            
            List<Long> adIds = DataTable.of(CreativeUnitIndex.class)
                    .selectAds(unitObjects);
            List<CreativeObject> creatives = DataTable.of(CreativeIndex.class)
                    .fetch(adIds);
            
            // Step 4: Filter creatives by AdSlot
            filterCreativeByAdSlot(
                    creatives,
                    adSlot.getWidth(),
                    adSlot.getHeight(),
                    adSlot.getType()
            );
            
            adSlot2Ads.put(
                    adSlot.getAdSlotCode(), buildCreativeResponse(creatives)
            );
        }
        
        log.info("fetchAds: {}-{}",
                JSON.toJSONString(request),
                JSON.toJSONString(response));
        
        return response;
    }
    
    private Set<Long> getORRelationUnitIds(Set<Long> adUnitIdSet,
                                           KeywordFeature keywordFeature,
                                           DistrictFeature districtFeature,
                                           ItFeature itFeature) {
        
        if (CollectionUtils.isEmpty(adUnitIdSet)) {
            return Collections.emptySet();
        }
        
        Set<Long> keywordUnitIdSet = new HashSet<>(adUnitIdSet);
        Set<Long> districtUnitIdSet = new HashSet<>(adUnitIdSet);
        Set<Long> itUnitIdSet = new HashSet<>(adUnitIdSet);
        
        filterKeywordFeature(keywordUnitIdSet, keywordFeature);
        filterDistrictFeature(districtUnitIdSet, districtFeature);
        filterItTagFeature(itUnitIdSet, itFeature);
        
        return new HashSet<>(
                CollectionUtils.union(
                        CollectionUtils.union(keywordUnitIdSet, districtUnitIdSet),
                        itUnitIdSet
                )
        );
    }
    
    
    private void filterKeywordFeature(
            Collection<Long> adUnitIds,
            KeywordFeature keywordFeature
    ) {
        
        if (CollectionUtils.isEmpty(adUnitIds)) {
            return;
        }
        
        if (CollectionUtils.isNotEmpty(keywordFeature.getKeywords())) {
            
            CollectionUtils.filter(
                    adUnitIds,
                    adUnitId ->
                            DataTable.of(UnitKeywordIndex.class)
                                    .match(adUnitId,
                                            keywordFeature.getKeywords())
            );
        }
    }
    
    private void filterDistrictFeature(
            Collection<Long> adUnitIds,
            DistrictFeature districtFeature
    ) {
        if (CollectionUtils.isEmpty(adUnitIds)) {
            return;
        }
        
        if (CollectionUtils.isNotEmpty(districtFeature.getDistricts())) {
            
            CollectionUtils.filter(
                    adUnitIds,
                    adUnitId ->
                            DataTable.of(UnitDistrictIndex.class)
                                    .match(adUnitId,
                                            districtFeature.getDistricts())
            );
        }
    }
    
    private void filterItTagFeature(
            Collection<Long> adUnitIds,
            ItFeature itFeature
    ) {
        
        if (CollectionUtils.isEmpty(adUnitIds)) {
            return;
        }
        
        if (CollectionUtils.isNotEmpty(itFeature.getIts())) {
            
            CollectionUtils.filter(
                    adUnitIds,
                    adUnitId ->
                            DataTable.of(UnitItIndex.class)
                                    .match(adUnitId,
                                            itFeature.getIts())
            );
        }
    }
    
    private void filterAdUnitAndPlanStatus(List<AdUnitObject> unitObjects,
                                           CommonStatus status) {
        
        if(CollectionUtils.isEmpty(unitObjects)) {
            return;
        }
        
        CollectionUtils.filter(
                unitObjects,
                object -> object.getUnitStatus().equals(status.getStatus())
                && object.getAdPlanObject().getPlanStatus().equals(status.getStatus())
        );
    }
    
    private void filterCreativeByAdSlot(List<CreativeObject> creatives,
                                        Integer width,
                                        Integer height,
                                        List<Integer> type) {
        
        if (CollectionUtils.isEmpty(creatives)) {
            return;
        }
        
        CollectionUtils.filter(
                creatives,
                creative ->
                        creative.getAuditStatus().equals(CommonStatus.VALID.getStatus())
                                && creative.getWidth().equals(width)
                                && creative.getHeight().equals(height)
                                && type.contains(creative.getType())
        );
    }
    
    private List<SearchResponse.Creative> buildCreativeResponse(
            List<CreativeObject> creatives
    ) {
        
        if (CollectionUtils.isEmpty(creatives)) {
            return Collections.emptyList();
        }
        
        // random pick one, this depends on the requirement
        CreativeObject randomObject = creatives.get(
                Math.abs(new Random().nextInt()) % creatives.size()
        );
        
        return Collections.singletonList(
                SearchResponse.convert(randomObject)
        );
    }
}
