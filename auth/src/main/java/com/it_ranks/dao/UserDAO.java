package com.it_ranks.dao;

import com.it_ranks.entity.User;

public interface UserDAO {
    User findByUserName(String userName);
    void save(User user);
}
