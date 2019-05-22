package com.bigtony.ad.service;

import com.bigtony.ad.vo.CreativeRequest;
import com.bigtony.ad.vo.CreativeResponse;

public interface ICreativeService {

    CreativeResponse createCreative(CreativeRequest request);
}
