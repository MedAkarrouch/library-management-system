package com.example.library_management_system.aspect;

import com.example.library_management_system.entity.User;
import com.example.library_management_system.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class UserAspect {
    private UserService userService;
    private HttpServletRequest request;
    @Autowired
    public UserAspect(UserService userService,HttpServletRequest request){
        this.userService = userService;
        this.request = request;
    }
    @Pointcut("execution(* com.example.library_management_system.controller.AuthController.*(..))")
    public void authController(){}
    @Before("execution(* com.example.library_management_system.controller.*.*(..)) && ! authController()")
    public void addLoggedInUserToRequest() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            String email = authentication.getName();
            User user = userService.getByEmail(email);
            request.setAttribute("user", user);
        }
    }

}
