package com.gdas.shopadminapi.request.application;

import com.gdas.shopadminapi.request.application.ports.in.FindRequestsUseCase;
import com.gdas.shopadminapi.request.application.ports.out.FindRequestsByDueDateBetweenPort;
import com.gdas.shopadminapi.request.domain.Request;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class FindRequestsService implements FindRequestsUseCase {

    private final FindRequestsByDueDateBetweenPort findRequestsByDueDateBetweenPort;

    FindRequestsService(FindRequestsByDueDateBetweenPort findRequestsByDueDateBetweenPort) {
        this.findRequestsByDueDateBetweenPort = findRequestsByDueDateBetweenPort;
    }

    @Override
    @Transactional
    public List<Request> apply(Command command) {
        return findRequestsByDueDateBetweenPort.findAllBetween(command.startDate(), command.endDate());
    }

}
