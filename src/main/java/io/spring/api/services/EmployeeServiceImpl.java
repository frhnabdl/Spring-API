package io.spring.api.services;

import java.util.List;

import org.springframework.stereotype.Service;

import io.spring.api.models.Employee;
import io.spring.api.repositories.IEmployeeRepository;

@Service
public class EmployeeServiceImpl implements IEmployeeService{

    private IEmployeeRepository employeeRepository;

    public EmployeeServiceImpl(IEmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
    
    @Override
    public List<Employee> Get() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee Get(Integer id) {
        return employeeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Data Employee Tidak Ditemukan"));
    }

    @Override
    public Boolean Save(Employee employee) {
        employeeRepository.save(employee);
        return employeeRepository.findById(employee.getId()).isPresent();
    }

    @Override
    public Boolean Delete(Integer id) {
        employeeRepository.deleteById(id);
        return !employeeRepository.findById(id).isPresent();
    }

}