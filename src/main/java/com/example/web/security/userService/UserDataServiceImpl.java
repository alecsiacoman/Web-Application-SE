package com.example.web.security.userService;

import com.example.web.models.Role;
import com.example.web.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class UserDataServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user;
        if("administrator@courtreserve.com".equals(email)){
            user = User.builder()
                    .email(email)
                    .password("123")
                    .role(Role.ADMINISTRATOR)
                    .name("administrator")
                    .build();
        }else {
            user = userRepository.findByEmail(email).get();
        }
        if(user == null)
            throw new UsernameNotFoundException("User Not Found with username: " + email);

        return UserDataImpl.build(user);
//        User user = userRepository.findByEmail(email)
//                .orElseThrow(() -> new UsernameNotFoundException("Username not found: " + email));
//        return UserDataImpl.build(user);
    }
}
