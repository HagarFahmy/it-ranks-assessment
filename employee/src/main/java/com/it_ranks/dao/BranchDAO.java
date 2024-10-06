package com.it_ranks.dao;

import com.it_ranks.entity.Branch;

import java.util.List;

public interface BranchDAO {
    Branch findById(Long id);
    void save(Branch branch);
    List<Branch> findAll();
    void delete(Branch branch );
    Branch update(Branch branch);

}
