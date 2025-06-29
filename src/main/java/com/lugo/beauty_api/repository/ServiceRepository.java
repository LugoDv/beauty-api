package com.lugo.beauty_api.repository;

import com.lugo.beauty_api.model.ServicesBeauty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<ServicesBeauty,Long> {
}
