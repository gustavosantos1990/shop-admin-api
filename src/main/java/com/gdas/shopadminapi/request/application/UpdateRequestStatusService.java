package com.gdas.shopadminapi.request.application;

import com.gdas.shopadminapi.request.application.ports.in.UpdateRequestStatusUseCase;
import com.gdas.shopadminapi.request.application.ports.out.FindRequestByIdPort;
import com.gdas.shopadminapi.request.application.ports.out.SaveRequestPort;
import com.gdas.shopadminapi.request.domain.Request;
import com.gdas.shopadminapi.request.domain.enummeration.RequestStatus;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static com.gdas.shopadminapi.request.domain.enummeration.RequestStatus.ACTIVE;
import static com.gdas.shopadminapi.request.domain.enummeration.RequestStatus.DELIVERED;
import static java.lang.String.format;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.PRECONDITION_FAILED;

@Service
class UpdateRequestStatusService implements UpdateRequestStatusUseCase {

    private record Operation(RequestStatus current, RequestStatus target) {
        Operation(@NotNull RequestStatus current, @NotNull RequestStatus target) {
            this.current = current;
            this.target = target;
        }
    }

    private final FindRequestByIdPort findRequestByIdPort;
    private final SaveRequestPort saveRequestPort;

    UpdateRequestStatusService(FindRequestByIdPort findRequestByIdPort, SaveRequestPort saveRequestPort) {
        this.findRequestByIdPort = findRequestByIdPort;
        this.saveRequestPort = saveRequestPort;
    }

    @Override
    @Transactional
    public Request apply(Request request) {
        Request existingRequest = findRequestByIdPort.findById(request.getId())
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, format("Invalid request id: #%s", request.getId())));

        if (existingRequest.getStatus().equals(request.getStatus())) {
            throw new ResponseStatusException(PRECONDITION_FAILED, format("desired and current statuses are equal (%s)", existingRequest.getStatus()));
        }

        Operation operation = new Operation(existingRequest.getStatus(), request.getStatus());

        switch (operation.target) {
            case DELIVERED -> updateToDelivered(existingRequest);
            default -> throw new ResponseStatusException(PRECONDITION_FAILED, format("invalid status update: %s -> %s",
                    operation.current, operation.target));
        }

        return saveRequestPort.save(existingRequest);
    }

    private void updateToDelivered(Request existingRequest) {
        List<RequestStatus> allowedPreviousStatues = List.of(ACTIVE);

        if (!allowedPreviousStatues.contains(existingRequest.getStatus())) {
            throw new ResponseStatusException(PRECONDITION_FAILED,
                    format("can't update status, current status must be one of: %s", allowedPreviousStatues));
        }

        existingRequest.setStatus(DELIVERED);
    }

}
