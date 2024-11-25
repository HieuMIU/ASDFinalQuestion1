package edu.miu.cse.vsms.service.impl;

import edu.miu.cse.vsms.dto.request.ServiceRequestDto;
import edu.miu.cse.vsms.dto.response.VehicleServiceResponseDto;
import edu.miu.cse.vsms.exception.ResourceNotFoundException;
import edu.miu.cse.vsms.model.Employee;
import edu.miu.cse.vsms.model.VService;
import edu.miu.cse.vsms.repository.EmployeeRepository;
import edu.miu.cse.vsms.repository.VehicleServiceRepository;
import edu.miu.cse.vsms.service.VehicleService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

import java.util.List;


@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class VehicleServiceImpl implements VehicleService {

    private final VehicleServiceRepository vehicleServiceRepository;
    private final EmployeeRepository employeeRepository;

    @Override
    public VehicleServiceResponseDto assignService(ServiceRequestDto request) {

        Employee employee = employeeRepository.findById(request.employeeId()).orElseThrow(() ->new EntityNotFoundException("Employee not found"));

        VService vService = new VService(request.serviceName(), request.cost(), request.vehicleType(), employee);
        VService saveVService = vehicleServiceRepository.save(vService);

        List<VService> vServices = employee.getVServices();
        vServices.add(saveVService);
        employee.setVServices(vServices);

        employeeRepository.save(employee);

        return new VehicleServiceResponseDto(vService.getId(), vService.getServiceName(), vService.getCost(), vService.getVehicleType());
    }
}
