package com.gdas.shopadminapi.request.adapter.out.persistence;

import com.gdas.shopadminapi.request.domain.Request;
import org.springframework.data.jpa.repository.JpaRepository;

interface RequestRepository extends JpaRepository<Request, Long> {
}
