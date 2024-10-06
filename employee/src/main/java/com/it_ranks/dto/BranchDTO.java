package com.it_ranks.dto;

import com.it_ranks.entity.Branch;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class BranchDTO {
    private Long id;
    private String name;
    public BranchDTO(Branch branch){
        this.name = branch.getBranchName();
        this.id= branch.getId();
    }
    public Branch convertTOEntity(){
        Branch branch = new Branch();
        branch.setId(this.id);
        branch.setBranchName(this.name);
        return branch;
    }
}
