package com.gdas.shopadminapi.request.application;

import com.gdas.shopadminapi.request.application.ports.in.CreateRequestUseCase;
import com.gdas.shopadminapi.request.application.ports.out.SaveCustomerPort;
import com.gdas.shopadminapi.request.application.ports.out.SaveRequestPort;
import com.gdas.shopadminapi.request.application.ports.out.FindCustomerByIdPort;
import com.gdas.shopadminapi.request.application.ports.out.FindCustomerByPhonePort;
import com.gdas.shopadminapi.request.domain.Customer;
import com.gdas.shopadminapi.request.domain.Request;
import com.gdas.shopadminapi.request.domain.enummeration.RequestStatus;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.springframework.beans.BeanUtils.copyProperties;

@Service
class CreateRequestService implements CreateRequestUseCase {

    private final SaveRequestPort saveRequestPort;
    private final SaveCustomerPort saveCustomerPort;
    private final FindCustomerByIdPort findCustomerByIdPort;
    private final FindCustomerByPhonePort findCustomerByPhonePort;

    CreateRequestService(SaveRequestPort saveRequestPort, SaveCustomerPort saveCustomerPort, FindCustomerByIdPort findCustomerByIdPort, FindCustomerByPhonePort findCustomerByPhonePort) {
        this.saveRequestPort = saveRequestPort;
        this.saveCustomerPort = saveCustomerPort;
        this.findCustomerByIdPort = findCustomerByIdPort;
        this.findCustomerByPhonePort = findCustomerByPhonePort;
    }

    @Override
    @Transactional
    public Request apply(Request request) {
        Customer customer = loadSaveCustomer(request.getCustomer());
        request.setCustomer(customer);
        request.setStatus(RequestStatus.ACTIVE);
        return saveRequestPort.save(request);
    }

    @Transactional
    private Customer loadSaveCustomer(Customer customer) {
        Optional<Customer> optionalCustomer = Optional.empty();

        if(customer.getId() != null) {
            optionalCustomer = findCustomerByIdPort.findById(customer.getId());
        }

        if (optionalCustomer.isEmpty()) {
            optionalCustomer = findCustomerByPhonePort.findByPhone(customer.getPhone());
        }

        if (optionalCustomer.isEmpty()) {
            return saveCustomerPort.save(customer);
        }

        Customer existingCustomer = optionalCustomer.get();
        copyProperties(customer, existingCustomer, "id", "createdAt");

        return saveCustomerPort.save(existingCustomer);
    }

}
