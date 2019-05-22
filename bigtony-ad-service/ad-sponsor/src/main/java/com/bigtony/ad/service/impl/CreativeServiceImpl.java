package com.bigtony.ad.service.impl;

import com.bigtony.ad.dao.CreativeRepository;
import com.bigtony.ad.entity.Creative;
import com.bigtony.ad.service.ICreativeService;
import com.bigtony.ad.vo.CreativeRequest;
import com.bigtony.ad.vo.CreativeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreativeServiceImpl implements ICreativeService {

    private final CreativeRepository creativeRepository;

    @Autowired
    public CreativeServiceImpl(CreativeRepository creativeRepository) {
        this.creativeRepository = creativeRepository;
    }

    @Override
    public CreativeResponse createCreative(CreativeRequest request) {

        Creative creative = creativeRepository.save(
                request.convertToEntity()
        );

        return new CreativeResponse(creative.getId(), creative.getName());
    }
}
