package com.gdas.shopadminapi.request.application;

import com.gdas.shopadminapi.request.application.ports.in.UpdateRequestStatusUseCase;
import com.gdas.shopadminapi.request.application.ports.out.FindRequestByIdPort;
import com.gdas.shopadminapi.request.application.ports.out.SaveRequestPort;
import com.gdas.shopadminapi.request.domain.Request;
import com.gdas.shopadminapi.request.domain.enummeration.RequestStatus;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

import static com.gdas.shopadminapi.common.domain.DateTimeUtils.currentLocalDateTime;
import static com.gdas.shopadminapi.request.domain.enummeration.RequestStatus.*;
import static java.lang.String.format;
import static org.apache.commons.lang3.ObjectUtils.firstNonNull;
import static org.springframework.http.HttpStatus.*;

@Service
class UpdateRequestStatusService implements UpdateRequestStatusUseCase {

    Logger logger = LoggerFactory.getLogger(UpdateRequestStatusService.class);

    private static final Map<RequestStatus, List<RequestStatus>> UPDATE_OPERATIONS = Map.of(
            ESTIMATE, List.of(ACTIVE, CANCELED),
            ACTIVE, List.of(DELIVERED, CANCELED)
    );

    private final FindRequestByIdPort findRequestByIdPort;
    private final SaveRequestPort saveRequestPort;

    UpdateRequestStatusService(FindRequestByIdPort findRequestByIdPort, SaveRequestPort saveRequestPort) {
        this.findRequestByIdPort = findRequestByIdPort;
        this.saveRequestPort = saveRequestPort;
    }

    @Override
    @Transactional
    public Request apply(Request request) {
        try {
            Request existingRequest = findRequestByIdPort.findById(request.getId())
                    .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, format("Invalid request id: #%s", request.getId())));

            statusInitialValidation(existingRequest, request);

            switch (existingRequest.getStatus()) {
                case ESTIMATE -> updateFromEstimate(existingRequest, request);
                case ACTIVE -> updateFromActive(existingRequest, request);
                default -> throwException(NOT_IMPLEMENTED, "operation not implemented");
            }

            return saveRequestPort.save(existingRequest);
        } catch (Throwable t) {
            logger.error("error on request status update: {}", request);
            logger.error(t.getMessage(), t);
            throw t;
        }
    }

    private void statusInitialValidation(Request existingRequest, Request request) {
        if (DONE.equals(existingRequest.getStatus()) || CANCELED.equals(existingRequest.getStatus())) {
            throwException(412, format("current status is non updatable (%s)", existingRequest.getStatus()));
        }

        if (existingRequest.getStatus().equals(request.getStatus())) {
            throwException(412, format("desired and current statuses are equal (%s)",existingRequest.getStatus()));
        }
    }

    private void updateFromEstimate(Request existingRequest, Request request) {
        checkIfUpdateIsPossible(ESTIMATE, request);

        if(CANCELED.equals(request.getStatus())) {
            existingRequest.setCanceledAt(currentLocalDateTime());
        } else {
            if (existingRequest.getRequestProducts() == null || existingRequest.getRequestProducts().isEmpty()) {
                throwException(422, "request must have at least one product");
            }
        }

        existingRequest.setStatus(request.getStatus());
    }

    private void updateFromActive(Request existingRequest, Request request) {
        checkIfUpdateIsPossible(ACTIVE, request);

        existingRequest.setStatus(request.getStatus());

        checkCancel(existingRequest, request);
    }

    private void checkIfUpdateIsPossible(RequestStatus currentStatus, Request request) {
        if(!UPDATE_OPERATIONS.get(currentStatus).contains(request.getStatus())) {
            throwException(412, format("can't update status from %s to %s, available statuses are: %s",
                    currentStatus, request.getStatus(), UPDATE_OPERATIONS.get(currentStatus)));
        }
    }

    private void checkCancel(Request existingRequest, Request request) {
        if(CANCELED.equals(request.getStatus())) {
            existingRequest.setCanceledAt(currentLocalDateTime());
        }
    }

    private void throwException(HttpStatus status, String message) {
        throw new ResponseStatusException(status, message);
    }

    private void throwException(int statusCode, String message) {
        HttpStatus httpStatus = firstNonNull(HttpStatus.resolve(statusCode), INTERNAL_SERVER_ERROR);
        throwException(httpStatus, message);
    }

}
