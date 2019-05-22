package com.bigtony.ad.service;

import com.bigtony.ad.exception.AdException;
import com.bigtony.ad.vo.CreateUserRequest;
import com.bigtony.ad.vo.CreateUserResponse;

public interface IUserService {
    
    CreateUserResponse createUser(CreateUserRequest request)
            throws AdException;
}
