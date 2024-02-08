package com.gdas.shopadminapi.product.application.ports.out;

import com.gdas.shopadminapi.acquisition.domain.ComponentAcquisition;

public interface CreateComponentAcquisitionPort {
    ComponentAcquisition create(ComponentAcquisition componentAcquisition);
}
