package com.lugo.beauty_api.service;

import com.lugo.beauty_api.model.ServicesBeauty;
import com.lugo.beauty_api.repository.ServiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServicesBeautyServiceImp implements ServicesBeautyService {

    private final ServiceRepository repository;

    @Override
    public ServicesBeauty save(ServicesBeauty service) {
        return repository.save(service);
    }

    @Override
    public ServicesBeauty update(ServicesBeauty service) {
        if (!repository.existsById(service.getId())) {
            throw new RuntimeException("Service not found");
        }
        return repository.save(service);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public ServicesBeauty findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Service not found"));
    }

    @Override
    public List<ServicesBeauty> findAll() {
        return repository.findAll();
    }
}