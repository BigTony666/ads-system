package com.bigtony.ad.search;

import com.bigtony.ad.search.vo.SearchRequest;
import com.bigtony.ad.search.vo.SearchResponse;

public interface ISearch {

    SearchResponse fetchAds(SearchRequest request);
}
