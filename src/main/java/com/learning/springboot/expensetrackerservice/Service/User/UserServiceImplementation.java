package com.learning.springboot.expensetrackerservice.Service.User;

import com.learning.springboot.expensetrackerservice.Models.User;
import com.learning.springboot.expensetrackerservice.Models.UserPrincipal;
import com.learning.springboot.expensetrackerservice.Repo.UserRepo;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImplementation implements UserService, UserDetailsService {

    private final UserRepo userRepo;
    private final AuthenticationManager authManager;
    private final BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder(12);

    public UserServiceImplementation(UserRepo userRepo, @Lazy AuthenticationManager authManager) {
        this.userRepo = userRepo;
        this.authManager = authManager;
    }

    @Override
    public void signUp(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(user);
    }

    @Override
    public void deleteUser(String userName) {
        userRepo.deleteByUsername(userName);
    }

    @Override
    public String logIn(User user) {
        Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(
                user.getUsername(), user.getPassword()
            )
        );
        if(authentication.isAuthenticated())
           return "Success";
        return "Fail";
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User users = userRepo.findByUsername(username);
        if (users == null)
            throw new UsernameNotFoundException("User Not Found");
        return new UserPrincipal(users);
    }
}
