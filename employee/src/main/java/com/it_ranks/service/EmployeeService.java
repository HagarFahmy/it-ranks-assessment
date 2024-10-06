package com.it_ranks.service;

import com.it_ranks.dao.BranchDAOImpl;
import com.it_ranks.dao.EmployeeDAOImpl;
import com.it_ranks.dto.EmployeeDTO;
import com.it_ranks.entity.Branch;
import com.it_ranks.entity.Employee;
import com.it_ranks.exception.exceptions.ItemNotFoundException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.management.InstanceNotFoundException;
import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class EmployeeService {
    private final EmployeeDAOImpl employeeDAO;
    private final BranchDAOImpl branchDAO;

    public EmployeeDTO save(EmployeeDTO dto){
        Branch branch= branchDAO.findById(dto.getBranchId());
        if(branch==null) throw new ItemNotFoundException ( "invalid branch id " );
        Employee employee= dto.convertToEntity();
        employee.setBranch(branch);
       return new EmployeeDTO (employeeDAO.save(employee));
    }
    public List<EmployeeDTO> getAllEmployee(){
        List<EmployeeDTO> employeeDTOS= employeeDAO
                .findAll()
                .stream()
                .map(EmployeeDTO::new).collect(Collectors.toList());
        return employeeDTOS;
    }

    public EmployeeDTO findById(Long id)  {
        Employee employee=employeeDAO.findById(id);
        if (employee==null) throw new ItemNotFoundException ("this Employee doesn't exist ");
        return new EmployeeDTO(employee);
    }

    public EmployeeDTO update(EmployeeDTO employeeDTO){
        Employee employee = employeeDAO.findById(employeeDTO.getId());
        if (employee==null) throw new RuntimeException("this Employee doesn't exist ");
        Branch branch= branchDAO.findById(employeeDTO.getBranchId());
        employee.setBranch(branch);
        return new EmployeeDTO(employeeDAO.update(employee));
    }
    public void deleteById(Long id)  {
        Employee employee=employeeDAO.findById(id);
        if (employee==null) throw new ItemNotFoundException ("this Employee doesn't exist ");
        employeeDAO.delete(employee);
    }

}
