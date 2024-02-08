package com.gdas.shopadminapi.acquisition.adapter.out.persistence;

import com.gdas.shopadminapi.acquisition.domain.ComponentAcquisition;
import org.springframework.data.jpa.repository.JpaRepository;

interface ComponentAcquisitionRepository extends JpaRepository<ComponentAcquisition, Long> {
}
