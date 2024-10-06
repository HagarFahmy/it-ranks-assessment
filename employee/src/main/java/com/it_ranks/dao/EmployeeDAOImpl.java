package com.it_ranks.dao;


import com.it_ranks.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
public class EmployeeDAOImpl implements EmployeeDAO{

    private final EntityManager entityManager;
    @Override
    public List<Employee> findAll() {
      return entityManager.createQuery("from Employee",Employee.class)
                .getResultList();
    }

    @Override
    @Transactional
    public Employee save(Employee employee) {
         entityManager.persist(employee);
         return employee;
    }

    @Override
    public Employee findById(Long id) {
        return entityManager.find(Employee.class,id);
    }

    @Override
    @Transactional
    public Employee update(Employee employee) {
        return entityManager.merge(employee);
    }

    @Override
    public void delete(Employee employee) {
         entityManager.remove(employee);

    }

}
