package com.bigtony.ad.dao;

import com.bigtony.ad.entity.AdUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdUserRepository extends JpaRepository<AdUser, Long> {
    
    AdUser findByUsername(String username);
}
