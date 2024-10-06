package com.it_ranks.service;

import com.it_ranks.dao.BranchDAOImpl;
import com.it_ranks.dto.BranchDTO;
import com.it_ranks.entity.Branch;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BranchService {
    private final BranchDAOImpl branchDAO;

    public BranchDTO findById(Long id){
        Branch branch= branchDAO.findById(id);
        if (branch==null) throw new RuntimeException("this branch doesn't exist ");
        return new BranchDTO(branch);
    }
    public List<BranchDTO> getAllBranches(){
        List<BranchDTO> branchDTOS= branchDAO.findAll().stream()
                .map(BranchDTO::new).collect(Collectors.toList());
        return branchDTOS;
    }

    public void save(BranchDTO branchDTO){
        Branch branch= branchDTO.convertTOEntity ( );
        branchDAO.save (branch);

    }

    public void deleteById(Long id)  {
      Branch branch=branchDAO.findById(id);
        if (branch==null) throw new RuntimeException("this branch doesn't exist ");
        branchDAO.delete(branch);
    }

    public BranchDTO update (BranchDTO branchDTO) {
        Branch branch=branchDAO.findById(branchDTO.getId ( ));
        if (branch==null) throw new RuntimeException("this branch doesn't exist ");
        branch.setBranchName (branchDTO.getName ());
        return new BranchDTO (branchDAO.update (branch));
    }
}
