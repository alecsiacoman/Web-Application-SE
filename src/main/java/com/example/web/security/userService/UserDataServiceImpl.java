package com.example.web.security.userService;

import com.example.web.models.Role;
import com.example.web.models.Users;
import com.example.web.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDataServiceImpl implements UserDetailsService {

    @Autowired
    UsersRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = null;
        if("admin".equals(username)){
            user = Users.builder()
                    .username(username)
                    .password("admin")
                    .role(Role.ADMINISTRATOR)
                    .build();
        }
//        else {
//            user = userRepository.findByEmail(email).get();
//         }
        if(user == null)
            throw new UsernameNotFoundException("User Not Found with username: " + username);

        return UserDataImpl.build(user);
//        User user = userRepository.findByEmail(email)
//                .orElseThrow(() -> new UsernameNotFoundException("Username not found: " + email));
//        return UserDataImpl.build(user);
    }
}
