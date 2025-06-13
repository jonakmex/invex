package com.invex.port.repository;

import com.invex.domain.entity.Employee;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface EmployeeRepository extends ReactiveCrudRepository<Employee, Long> {


}
