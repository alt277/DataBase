package com.example.demo.service.impl;

import com.example.demo.persist.entity.Role;
import com.example.demo.persist.entity.User;
import com.example.demo.persist.entity.data.UserRequestDTO;
import com.example.demo.persist.repository.InfoRepository;
import com.example.demo.persist.repository.RoleRepository;
import com.example.demo.persist.repository.UserDataRepository;
import com.example.demo.persist.repository.UserRepository;
import com.example.demo.service.UserService;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserDataRepository userDataRepository;
    private final InfoRepository infoRepository;
 //   private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository,
                           UserDataRepository userDataRepository, InfoRepository infoRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userDataRepository = userDataRepository;
        this.infoRepository = infoRepository;
    }

    @Override
    public User createUser(UserRequestDTO requestUser) {
        try {
            return    userRepository.save(buildUser(requestUser));
        } catch (DataIntegrityViolationException e) {
            throw new EmailIsNotUnique();
        }
      //  userRepository.save(buildUser(requestUser));
    }

    //  загружаем нового SP Security User  в SecurityContextHolder
    public void authenticateUser(User user) {
        List<Role> roles = user.getRoles().stream().distinct().collect(Collectors.toList());
        List<GrantedAuthority> authorities = roles.stream()
                .map(p -> new SimpleGrantedAuthority(p.getName()))
                .collect(Collectors.toList());

        Authentication authentication = new UsernamePasswordAuthenticationToken(new org.springframework.security.core.userdetails.User(
                user.getEmail(), user.getPassword(), authorities),
                null,
                authorities);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }


    @Override
    public User updateUser(UserRequestDTO userDTO) {

             /*  getOne(id); получает  только ссылочный (прокси) объект и не извлекает его из БД.
               В этой ссылке  можно указать, что необходимо, и в save() он будет выполнять только инструкцию SQL UPDATE*/
        Integer id = userRepository.findByEmail(userDTO.getEmail()).orElseThrow().getId();
        User userToUpdate = userRepository.getOne(id);

        userToUpdate.setEmail(userDTO.getEmail());
      //  userToUpdate.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        userToUpdate.setName(userDTO.getName());
        userToUpdate.setLastName(userDTO.getLastName());
        userToUpdate.setAge(userDTO.getAge());


        userToUpdate.setRoles(assignRoles(userDTO.getRoles()));

        return userRepository.save(userToUpdate);
    }


    @Override
    public void deleteUser(String email) {
        userRepository.deleteByEmail(email);
    }

    @Override
    public List<User> showUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(String email) {
        return userRepository.findByEmail(email).orElseThrow();
    }
    @Override
    public UserRequestDTO getUserDTO(String email) {
        User user= userRepository.findByEmail(email).orElseThrow();

        return UserRequestDTO.builder().
                email(user.getEmail()).
                name(user.getName()).
                lastName(user.getLastName()).
                age(user.getAge()).
                roleList(user.getRoles().stream().collect(Collectors.toList())).
                build();
    }

    public User buildUser(UserRequestDTO userRequest) {


        return User.builder().
                email(userRequest.getEmail()).

                name(userRequest.getName()).
                lastName(userRequest.getLastName()).
                age(userRequest.getAge()).
                roles(assignRoles(userRequest.getRoles())).
                build();
    }

    public Collection<Role> assignRoles(List<String> roles) {

        Collection<Role> roleCollection = new ArrayList<>();
        for (String roleName : roles
        ) {
            Role role = roleRepository.findRoleByName(roleName);
            roleCollection.add(role);
        }
        return roleCollection;
    }


    public UserRequestDTO findBestSellerByDate(LocalDate create_date){
        Integer id=  infoRepository.findBestUserIdByDate(create_date);
        return userDataRepository.findUserById(id).get();
    };
}


