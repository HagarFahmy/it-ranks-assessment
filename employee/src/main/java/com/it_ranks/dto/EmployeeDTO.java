package com.it_ranks.dto;


import com.it_ranks.entity.Employee;
import com.it_ranks.validation.annotaion.UpperCase;
import com.it_ranks.validation.annotaion.ValidAgeFromNationalID;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {
    private Long id;
    @NotNull
    @NotBlank
    @Pattern(regexp ="^[A-Za-z]*$" )
    @UpperCase
    private String name;
    @NotNull
    @Min(value = 18)
    private Long age;

    @NotNull
    @NotBlank
    @Size(min=14, max=14)
//    @ValidAgeFromNationalID
    private String nationalId;
    private BranchDTO branch;
    @NotNull
    private Long branchId;

    public EmployeeDTO(Employee employee){
        this.id= employee.getId();
        this.name=employee.getName();
        this.branch = new BranchDTO(employee.getBranch());
        this.nationalId=employee.getNationalId();
        this.age=employee.getAge ();
    }
    public Employee convertToEntity(){
        Employee employee= new Employee();
        employee.setName(this.name);
        employee.setNationalId(this.nationalId);
        return employee;
    }
}
