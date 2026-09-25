package com.example.demo.controller;

import com.example.demo.dto.UserRegisterRequestDto;
import com.example.demo.dto.UserRegisterResponseDto;
import com.example.demo.service.AuthService;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/users")
public class UserController {

    private AuthService authService;

    public UserController(AuthService authService) {
        this.authService = authService;
    }

//    @Autowired
//    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @GetMapping("/hello")
    public String sayHello() {
//        System.out.println(passwordEncoder.encode("secret123"));
//        System.out.println(passwordEncoder.encode("secret123"));
//
//        System.out.println(passwordEncoder.matches(
//                "secret123",
//                "$2a$10$6BK.XCl/A/AMBwPjHosSyOICAF1kpAN0nO8.zzZXRj8sLp9NqKe.K"
//        ));
        return "Hello!";
    }

    @PostMapping("/register")
    public ResponseEntity<UserRegisterResponseDto> register(
            @RequestBody UserRegisterRequestDto requestDto) {

        UserRegisterResponseDto userRegisterResponseDto =
                authService.register(requestDto);

        return ResponseEntity.ok(userRegisterResponseDto);
    }
//
//    @GetMapping("/token")
//    public CsrfToken getToken(CsrfToken csrfToken) {
//        return csrfToken;
//    }
}
