package com.example.web.controller;


import com.example.web.models.LoginResponse;
import com.example.web.models.Role;
import com.example.web.models.Users;
import com.example.web.security.jwt.JwtUtils;
import com.example.web.security.userService.CurrentUser;
import com.example.web.security.userService.UserDataImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping(value = "/auth")
@RequiredArgsConstructor
public class AuthentificationController {

    private final AuthenticationManager authenticationManager;

    private final JwtUtils jwtUtils;

    private final PasswordEncoder encoder;
    //private final UserService userService;

//    @Autowired
//    public AuthentificationController(UserService userService, EmailService emailService) {
//        this.userService = userService;
//        this.emailService = emailService;
//    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody Users loginRequest) {
//        UserDTO userDTO = userService.findUserByEmail(loginRequest.getEmail());

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDataImpl userDetails = null;
        if(authentication.getPrincipal() instanceof UserDataImpl)
            userDetails = (UserDataImpl) authentication.getPrincipal();
        if(authentication.getPrincipal() instanceof org.springframework.security.core.userdetails.User)
            userDetails = new UserDataImpl((org.springframework.security.core.userdetails.User) authentication.getPrincipal());

        List<String> roles = userDetails != null ? userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList()) : null;
        System.out.println(roles);
        //Only the client will have the
        Role role;
        if (roles.get(0) == "ADMINISTRATOR")
            role = Role.ADMINISTRATOR;
        else
            role = Role.CLIENT;

        CurrentUser.getInstance().user = new Users(userDetails.getUser_id(),
                userDetails.getUsername(),
                userDetails.getPassword(),
                role);

        return ResponseEntity.ok(new LoginResponse(
                userDetails.getUser_id(),
                jwt,
                userDetails.getUsername(),
                userDetails.getPassword(),
                roles));
    }

//    @PostMapping("/signup")
//    public ResponseEntity<?> registerRegularUser(@Valid @RequestBody UserDTO regularUserDTO) {
//        regularUserDTO.setPassword(encoder.encode(regularUserDTO.getPassword()));
//        userService.insert(regularUserDTO);
//        Map<String,String> response = new HashMap<>();
//        response.put("email", regularUserDTO.getEmail());
//        return ResponseEntity.ok(response);
//    }
//
//    @PutMapping(value = "/resetPassword")
//    public ResponseEntity<?> resetPassword(@RequestBody ResetPasswdDTO resetPasswordDTO) throws Exception {
//        userService.resetPassword(resetPasswordDTO);
////        SimpleMailMessage passwordResetEmail = emailService.resetPasswordEmail(resetPasswordDTO);
////        emailService.sendEmail(passwordResetEmail);
//
//        Map<String,String> response = new HashMap<>();
//        response.put("password", "Successfully changed!");
//        return ResponseEntity.ok(response);
//    }
}
