package com.lugo.beauty_api.service;

import com.lugo.beauty_api.model.ServicesBeauty;

import java.util.List;

public interface ServicesBeautyService {

    public ServicesBeauty save(ServicesBeauty service);
    public ServicesBeauty update(ServicesBeauty service);
    public ServicesBeauty findById(Long id);
    public void delete(Long id);
    public List<ServicesBeauty> findAll();
}
