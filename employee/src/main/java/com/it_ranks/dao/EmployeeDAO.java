package com.it_ranks.dao;

import com.it_ranks.entity.Employee;
import java.util.List;

public interface EmployeeDAO {
    List<Employee> findAll();
    Employee save(Employee employee);

    Employee findById(Long id);

    Employee update(Employee employee);

    void delete(Employee employee);


}
