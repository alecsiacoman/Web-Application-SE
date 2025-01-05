package com.example.web.security.userService;

import com.example.web.models.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserDataImpl implements UserDetails {

    private static final long serialVersionUID = 1L;
    private Long user_id;
    private String password;
    private String email;
    private String name;

    private Collection<? extends GrantedAuthority> authorities;

    public UserDataImpl(Long user_id, String password, String email, String name,
                        Collection<? extends GrantedAuthority> authorities) {
        this.user_id = user_id;
        this.email = email;
        this.name = name;
        this.password = password;
        this.authorities = authorities;
    }

    public UserDataImpl(org.springframework.security.core.userdetails.User user){
        this.user_id = null;
        this.email = user.getUsername();
        this.name = "ADMINISTRATOR";
        this.password = user.getPassword();
        this.authorities = user.getAuthorities();
    }

    public static UserDataImpl build(User user) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        GrantedAuthority grantedAuthority = () -> user.getRole().toString();
        authorities.add(grantedAuthority);

        return new UserDataImpl(
                user.getUser_id(),
                user.getPassword(),
                user.getEmail(),
                user.getName(),
                authorities);
    }

    public Long getUser_id() {
        return user_id;
    }

    public String getEmail() {
        return email;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return null;
    }


    public String getName() {
        return name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
