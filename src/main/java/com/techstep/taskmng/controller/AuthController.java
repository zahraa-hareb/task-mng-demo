package com.techstep.taskmng.controller;

import com.techstep.taskmng.dto.RegisterRequest;
import com.techstep.taskmng.service.AppUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final AppUserService appUserService;

    // Only GET: the POST /login is handled by Spring Security's filter
    @GetMapping("/login")
    public String login() {
        return "auth/login";
    }

    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("registerRequest", new RegisterRequest());
        return "auth/register";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("registerRequest") RegisterRequest request,
                           BindingResult bindingResult) {
        if (appUserService.usernameExists(request.getUsername())) {
            bindingResult.rejectValue("username", "exists", "Username is already taken");
        }
        if (bindingResult.hasErrors()) {
            return "auth/register";
        }
        appUserService.register(request);
        return "redirect:/login?registered";
    }
}
