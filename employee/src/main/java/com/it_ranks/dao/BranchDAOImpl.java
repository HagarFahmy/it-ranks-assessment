package com.it_ranks.dao;

import com.it_ranks.entity.Branch;
import com.it_ranks.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@AllArgsConstructor
@Repository
public class BranchDAOImpl implements BranchDAO{
    private final EntityManager entityManager;

    @Override
    public Branch findById(Long id) {
        return entityManager.find(Branch.class,id);
    }

    @Override
    @Transactional
    public void save(Branch branch) {
        entityManager.persist (branch);
    }

    @Override
    public List<Branch> findAll ( ) {
         return entityManager.createQuery("from Branch", Branch.class)
                .getResultList();
    }

    @Override
    public void delete (Branch branch) {
        entityManager.remove (branch);
    }

    @Override
    @Transactional
    public Branch update (Branch branch) {
        return entityManager.merge (branch);
    }
}
