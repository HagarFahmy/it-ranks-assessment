package com.it_ranks.service;

import com.it_ranks.dao.UserDAOImpl;
import com.it_ranks.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserDAOImpl userDAO;
    @Override
    public UserDetails loadUserByUsername (String username) throws UsernameNotFoundException {
        return userDAO.findByUserName (username);
    }
    public void save(User user){
        userDAO.save (user);
    }
}
