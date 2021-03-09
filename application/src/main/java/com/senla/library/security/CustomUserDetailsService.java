package com.senla.library.security;
import com.senla.library.entity.User;
import com.senla.library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomUserDetailsService implements UserDetailsService {
    
    @Autowired
    private UserService userService;

    @Override
    public CustomUserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userService.findByLogin(userName);
        return CustomUserDetails.fromUserEntityToCustomUserDetails(user);
    }
}
