package io.spring.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.spring.api.models.Employee;

@Repository
public interface IEmployeeRepository extends JpaRepository<Employee, Integer> {
    
}
