package com.it_ranks.controller;

import com.it_ranks.dto.EmployeeDTO;
import com.it_ranks.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee")
@AllArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;
    @PostMapping("/add")
    public ResponseEntity addEmployee(@Valid @RequestBody EmployeeDTO dto){
        return  ResponseEntity.ok(employeeService.save(dto));
    }

    @GetMapping("/allEmployees")
    public ResponseEntity getAllEmployees(){
        return ResponseEntity.ok(employeeService.getAllEmployee());
    }

    @GetMapping("/{id}")
    public ResponseEntity getEmployeeById(@PathVariable Long id){
        return ResponseEntity.ok(employeeService.findById(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteEmployee(@PathVariable Long id){
        employeeService.deleteById(id);
        return ResponseEntity.ok("deleted");
    }

    @PutMapping("/update")
    public ResponseEntity updateEmployee(@Valid @RequestBody EmployeeDTO employeeDTO){
        return ResponseEntity.ok(employeeService.update(employeeDTO));
    }

}