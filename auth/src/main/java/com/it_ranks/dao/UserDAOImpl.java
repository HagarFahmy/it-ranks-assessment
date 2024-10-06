package com.it_ranks.dao;

import com.it_ranks.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;


@Repository
@RequiredArgsConstructor
public class UserDAOImpl implements UserDAO{
    private final EntityManager entityManager;
    @Override
    public User findByUserName (String userName) {
        TypedQuery<User> query = entityManager.createQuery ("from User where userName=:userName", User.class);
        query.setParameter ("userName",userName);
        return query.getSingleResult ();
    }

    @Override
    @Transactional
    public void save (User user) {
         entityManager.persist (user);
    }
}
