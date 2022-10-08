package com.example.demo.service;


import com.example.demo.persist.entity.Role;
import com.example.demo.persist.entity.User;
import com.example.demo.persist.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service("userDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional

    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User doesn't exists"));

        System.out.println("new SP SECURITY Username " + fromUser(user).getUsername());
        System.out.println("new SP SECURITY Password " + fromUser(user).getPassword());
        System.out.println("new SP SECURITY .getAuthorities())= Список прав: " + fromUser(user).getAuthorities());
        return fromUser(user);
    }

    public UserDetails fromUser(User user) {

        // отображаем список ролей в список GrantedAuthority
        List<Role> roles = user.getRoles().stream().distinct().collect(Collectors.toList());
        List<GrantedAuthority> authorityList = roles.stream()
                .map(p -> new SimpleGrantedAuthority(p.getName()))
                .collect(Collectors.toList());


        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),

                //  user.getRole().getAuthorities()
                authorityList
        );
    }


}
